package com.rocky.boot.flowable.listener;

import com.rocky.boot.common.enums.ResultCode;
import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.flowable.config.FlowableServiceProp;
import com.rocky.boot.flowable.constant.FlowableConstant;
import com.rocky.boot.flowable.enums.ExecutionStatus;
import com.rocky.boot.flowable.enums.TaskNotifyEvent;
import com.rocky.boot.flowable.enums.TaskStatus;
import com.rocky.boot.flowable.handler.TaskEventHandler;
import com.rocky.boot.flowable.handler.UserTaskHandler;
import com.rocky.boot.flowable.model.dto.ExecutionDTO;
import com.rocky.boot.flowable.model.resp.TaskHandleResp;
import com.rocky.boot.flowable.remote.TaskClientService;
import com.rocky.boot.flowable.utils.FeignClientServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author : rocky
 * @date : created in 2023/4/20
 */
@Slf4j
@Component
public class UserTaskGlobalListener implements TaskListener {

    @Resource
    private FlowableServiceProp flowableServiceProp;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskEventHandler taskEventHandler;

    @Resource
    private TaskService taskService;

    @Resource
    private FeignClientServiceUtil feignClientServiceUtil;

    @Resource
    private UserTaskHandler userTaskHandler;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private Expression serviceRef;

    @Resource
    private Expression timeCycle;

    @Override
    public void notify(DelegateTask delegateTask) {
        String processInstanceId = delegateTask.getProcessInstanceId();
        String taskName = delegateTask.getName();
        String taskId = delegateTask.getId();
        String taskKey = delegateTask.getTaskDefinitionKey();
        log.info("start a task {}, processInstanceId={}, taskId={}",
                taskName,
                processInstanceId,
                taskId);

        if (serviceRef == null) {
            throw new IllegalArgumentException("task is not find name serviceRef filed");
        }
        String serviceRefStr = (String) serviceRef.getValue(delegateTask);
        String[] split = serviceRefStr.split(":");
        String serviceName = split[0];
        String beanName = split[1];

        String repeat = flowableServiceProp.getRepeat();
        if (timeCycle != null) {
            String timeCycleStr = (String) timeCycle.getValue(delegateTask);
            if (StringUtils.isNotBlank(timeCycleStr)) {
                repeat = timeCycleStr;
            }
        }

        try {
            // 任务通知
            ProcessDefinition processDefinition = repositoryService.getProcessDefinition(delegateTask.getProcessDefinitionId());
            String processDefinitionKey = processDefinition.getKey();
            taskService.setAssignee(taskId, FlowableConstant.EXECUTION_SYSTEM_USER);
            taskEventHandler.notify(serviceName, beanName, processDefinitionKey, processInstanceId, delegateTask.getExecutionId(),
                    taskId, taskKey, TaskNotifyEvent.TASK_START, "");
            // 设置任务变量
            taskService.setVariable(taskId, FlowableConstant.TASK_STATUS_KEY, ExecutionStatus.EXECUTING.name());
            // 执行任务
            TaskClientService taskRemoteService = feignClientServiceUtil.getRemoteService(serviceName, TaskClientService.class);
            ExecutionDTO dto = new ExecutionDTO();
            dto.setProcessDefinitionKey(processDefinitionKey);
            dto.setProcessInstanceId(processInstanceId);
            dto.setProcessVariables(runtimeService.getVariables(processInstanceId));
            dto.setExecutionId(delegateTask.getExecutionId());
            dto.setTaskKey(taskKey);
            dto.setTaskId(taskId);
            BaseResult<TaskHandleResp> result = taskRemoteService.taskExecute(beanName, dto);
            if (result != null && ResultCode.SUCCESS.getCode().equalsIgnoreCase(result.getCode())) {
                TaskHandleResp resp = result.getData();
                if (resp != null && resp.getUpdateVariables() != null) {
                    runtimeService.setVariables(processInstanceId, resp.getUpdateVariables());
                }
                String errorMessage = resp.getErrorMessage();
                if (resp != null && StringUtils.isNotBlank(errorMessage)) {
                    taskService.setVariableLocal(taskId, FlowableConstant.ERROR_MESSAGE, errorMessage);
                }
                // 判断任务状态
                TaskStatus status = resp.getStatus();
                log.info("任务状态:taskId={}, status={}", taskId, status);
                if (TaskStatus.EXECUTING.equals(status)) {
                    userTaskHandler.pollingTask(delegateTask, serviceName, beanName, repeat);
                } else if (TaskStatus.FINISH.equals(status)) {
                    userTaskHandler.completeTask(delegateTask, serviceName, beanName, errorMessage);
                } else {
                    userTaskHandler.failedTask(delegateTask, serviceName, beanName, errorMessage);
                }
            } else {
                String message = result != null ? result.getMessage() : "";
                log.error("执行任务失败, taskName={}, message={}", taskName, message);
                userTaskHandler.failedTask(delegateTask, serviceName, beanName, message);
            }
        } catch (Exception e) {
            log.error("执行任务异常, taskName={}", taskName, e);
            if (e instanceof IOException) {
                log.info("IOException异常，进入轮询接口，processInstanceId={}, taskId={}", processInstanceId, taskId);
                userTaskHandler.pollingTask(delegateTask, serviceName, beanName, repeat);
            } else {
                userTaskHandler.failedTask(delegateTask, serviceName, beanName, StringUtils.abbreviate(e.getMessage(), 500));
            }
        }
    }
}
