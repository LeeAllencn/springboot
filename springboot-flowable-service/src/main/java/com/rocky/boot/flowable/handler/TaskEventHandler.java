package com.rocky.boot.flowable.handler;

import com.rocky.boot.flowable.enums.EventStatus;
import com.rocky.boot.flowable.enums.TaskNotifyEvent;
import com.rocky.boot.flowable.model.dto.TaskNotifyDTO;
import com.rocky.boot.flowable.remote.TaskNotifyClientService;
import com.rocky.boot.flowable.utils.FeignClientServiceUtil;
import com.rocky.boot.flowable.utils.FlowableEventUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : rocky
 * @date : created in 2023/4/20
 */
@Slf4j
@Component
public class TaskEventHandler {

    @Resource
    private FeignClientServiceUtil feignClientServiceUtil;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    public void notify(String serviceName,
                       String beanName,
                       String processDefinitionKey,
                       String processInstanceId,
                       String executionId,
                       String taskId,
                       String taskKey,
                       TaskNotifyEvent event,
                       String message) {

        try {
            // 任务通知
            TaskNotifyClientService taskNotifyRemoteService = feignClientServiceUtil.getRemoteService(serviceName, TaskNotifyClientService.class);
            TaskNotifyDTO dto = new TaskNotifyDTO();
            dto.setProcessDefinitionKey(processDefinitionKey);
            dto.setProcessInstanceId(processInstanceId);
            dto.setProcessVariables(runtimeService.getVariables(processInstanceId));
            dto.setExecutionId(executionId);
            dto.setTaskId(taskId);
            dto.setTaskKey(taskKey);
            dto.setEvent(event);
            dto.setMessage(message);
            taskNotifyRemoteService.notify(beanName, dto);
            // 设置变量
            taskService.setVariableLocal(taskId, FlowableEventUtil.getTaskEventStatusKey(event), EventStatus.SUCCESS);
        } catch (Exception e) {
            log.error("任务通知时间失败:serviceName={}, beanName={}, taskId={}, taskKey={}, event={}",
                    serviceName, beanName, taskId, taskKey, event, e);
            taskService.setVariableLocal(taskId, FlowableEventUtil.getTaskEventStatusKey(event), EventStatus.FAILED);
            taskService.setVariableLocal(taskId, FlowableEventUtil.getTaskEventMessageKey(event), StringUtils.abbreviate(e.getMessage(), 500));
        }
    }
}
