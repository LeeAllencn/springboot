package com.rocky.boot.flowable.jobexecutor;

import com.rocky.boot.common.enums.ResultCodeEnum;
import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.flowable.constant.FlowableConstant;
import com.rocky.boot.flowable.enums.TaskStatus;
import com.rocky.boot.flowable.handler.UserTaskHandler;
import com.rocky.boot.flowable.model.dto.ExecutionDTO;
import com.rocky.boot.flowable.model.resp.TaskHandleResp;
import com.rocky.boot.flowable.remote.TaskClientService;
import com.rocky.boot.flowable.utils.FeignClientServiceUtil;
import com.rocky.boot.flowable.utils.TimerJobHandlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.job.service.JobHandler;
import org.flowable.job.service.impl.persistence.entity.JobEntity;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.flowable.variable.api.delegate.VariableScope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author : rocky
 * @date : created in 2023/4/21
 */
@Slf4j
@Component("taskQueryTimerJobHandler")
public class TaskQueryTimerJobHandler implements JobHandler {

    public static final String TYPE = "query-task-timer";

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private FeignClientServiceUtil feignClientServiceUtil;

    @Resource
    private UserTaskHandler userTaskHandler;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void execute(JobEntity job, String configuration, VariableScope variableScope, CommandContext commandContext) {
        log.info("开始执行任务结果查询定时器作业，job={}", job);

        String taskId = TimerJobHandlerUtil.getTaskId(configuration);
        String serviceName = TimerJobHandlerUtil.getServiceName(configuration);
        String beanName = TimerJobHandlerUtil.getBeanName(configuration);

        String processInstanceId = job.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            log.warn("流程实例不存在,processInstanceId={}", processInstanceId);
            job.setRepeat(null);
            return;
        }

        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            log.warn("task不存在,taskId={}", taskId);
            job.setRepeat(null);
            return;
        }

        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        String processDefinitionKey = processDefinition.getKey();

        TaskClientService taskRemoteService = feignClientServiceUtil.getRemoteService(serviceName, TaskClientService.class);
        boolean isLast = job.getRepeat().startsWith("R1/");
        ExecutionDTO dto = new ExecutionDTO();
        dto.setTaskKey(task.getTaskDefinitionKey());
        dto.setTaskId(taskId);
        dto.setExecutionId(task.getExecutionId());
        dto.setProcessDefinitionKey(processDefinitionKey);
        dto.setProcessInstanceId(processInstanceId);
        dto.setProcessVariables(runtimeService.getVariables(processInstanceId));
        try {
            BaseResult<TaskHandleResp> result = taskRemoteService.taskQuery(beanName, isLast, dto);
            if (result != null && ResultCodeEnum.SUCCESS.getCode().equalsIgnoreCase(result.getCode())) {
                TaskHandleResp resp = result.getData();
                TaskStatus status = resp.getStatus();
                String errorMessage = resp.getErrorMessage();
                if (resp.getUpdateVariables() != null) {
                    runtimeService.setVariables(processInstanceId, resp.getUpdateVariables());
                }
                if (StringUtils.isNotBlank(errorMessage)) {
                    taskService.setVariableLocal(taskId, FlowableConstant.ERROR_MESSAGE, errorMessage);
                }
                if (TaskStatus.EXECUTING.equals(status)) {
                    if (isLast) {
                        userTaskHandler.failedTask(task, serviceName, beanName, "执行任务超时");
                    }
                } else if (TaskStatus.FINISH.equals(status)) {
                    userTaskHandler.completeTask(task, serviceName, beanName, errorMessage);
                    job.setRepeat(null);
                } else {
                    userTaskHandler.failedTask(task, serviceName, beanName, errorMessage);
                    job.setRepeat(null);
                }
            } else {
                userTaskHandler.failedTask(task, serviceName, beanName, Objects.requireNonNull(result).getMessage());
                job.setRepeat(null);
            }
        } catch (Exception e) {
            if (isLast) {
                userTaskHandler.failedTask(task, serviceName, beanName, e.getMessage());
                job.setRepeat(null);
            }
            log.error("任务处理异常, job={}", job, e);
        }
    }
}
