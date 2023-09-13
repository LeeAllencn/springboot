package com.rocky.boot.flowable.jobexecutor;

import com.rocky.boot.flowable.api.enums.ProcessNotifyEvent;
import com.rocky.boot.flowable.handler.ProcessEventHandler;
import com.rocky.boot.flowable.utils.TimerJobHandlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.job.service.JobHandler;
import org.flowable.job.service.impl.persistence.entity.JobEntity;
import org.flowable.variable.api.delegate.VariableScope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : rocky
 * @date : created in 2023/4/18
 */

@Slf4j
@Component("processNotifyRetryTimerJobHandler")
public class ProcessNotifyRetryTimerJobHandler implements JobHandler {

    public static final String TYPE = "retry-process-notify-timer";

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private ProcessEventHandler processEventHandler;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void execute(JobEntity job, String configuration, VariableScope variableScope, CommandContext commandContext) {
        log.info("开始执行流程通知重试定时器作业，job={}", job);
        String processInstanceId = job.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            log.warn("流程实例不存在,processInstanceId={}", processInstanceId);
            job.setRepeat(null);
            return;
        }
        // 重试流程通知
        String notifyEventName = TimerJobHandlerUtil.getNotifyEventName(configuration);
        String message = TimerJobHandlerUtil.getMessage(configuration);
        boolean notify = processEventHandler.notify(processInstance.getProcessDefinitionKey(),
                processInstanceId,
                job.getExecutionId(),
                ProcessNotifyEvent.valueOf(notifyEventName),
                message);
        if (notify) {
            job.setRepeat(null);
        }
        log.info("流程通知重试成功，processInstance={}", processInstance);
    }
}
