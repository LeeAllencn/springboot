package com.rocky.boot.flowable.jobexecutor;

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
 * @date : created in 2023/4/17
 */
@Slf4j
@Component("processDeleteTimerJobHandler")
public class ProcessDeleteTimerJobHandler implements JobHandler {

    public static final String TYPE = "delete-process-timer";

    @Resource
    private RuntimeService runtimeService;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void execute(JobEntity job, String configuration, VariableScope variableScope, CommandContext commandContext) {
        log.info("开始执行流程超时定时器作业，job={}", job);
        String processInstanceId = job.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            log.warn("流程实例不存在,processInstanceId={}", processInstanceId);
            job.setRepeat(null);
            return;
        }
        // 删除流程实例
        runtimeService.deleteProcessInstance(processInstanceId, "流程超时结束");
        job.setRepeat(null);
        log.info("流程超时，删除流程实例成功，processInstance={}", processInstance);
    }
}
