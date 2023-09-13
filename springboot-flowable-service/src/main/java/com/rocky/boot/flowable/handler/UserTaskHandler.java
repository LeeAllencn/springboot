package com.rocky.boot.flowable.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rocky.boot.flowable.api.enums.ProcessNotifyEvent;
import com.rocky.boot.flowable.constant.FlowableConstant;
import com.rocky.boot.flowable.enums.ExecutionStatus;
import com.rocky.boot.flowable.enums.ProcessStatus;
import com.rocky.boot.flowable.enums.TaskNotifyEvent;
import com.rocky.boot.flowable.jobexecutor.TaskQueryTimerJobHandler;
import com.rocky.boot.flowable.mapper.ActRuTimerJobMapper;
import com.rocky.boot.flowable.model.ActRuTimerJob;
import com.rocky.boot.flowable.service.IFlowableProcessRecordService;
import com.rocky.boot.flowable.utils.TimerJobHandlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.impl.calendar.DurationBusinessCalendar;
import org.flowable.common.engine.impl.runtime.Clock;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.job.api.Job;
import org.flowable.job.service.TimerJobService;
import org.flowable.job.service.impl.persistence.entity.TimerJobEntity;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

import static com.rocky.boot.flowable.utils.TimerJobHandlerUtil.*;

/**
 * @author : rocky
 * @date : created in 2023/4/20
 */
@Slf4j
@Component
public class UserTaskHandler {

    @Resource
    private TaskService taskService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TimerJobService timerJobService;

    @Resource
    private TaskEventHandler taskEventHandler;

    @Resource
    private ProcessEngineConfigurationImpl processEngineConfiguration;

    @Resource
    private ProcessEngine processEngine;

    @Resource
    private ProcessEventHandler processEventHandler;

    @Resource
    private IFlowableProcessRecordService flowableProcessRecordService;

    @Resource
    private ActRuTimerJobMapper actRuTimerJobMapper;

    public void completeTask(DelegateTask delegateTask, String serviceName, String beanName, String message) {
        String taskId = delegateTask.getId();
        taskService.setVariableLocal(taskId, FlowableConstant.TASK_STATUS_KEY, ExecutionStatus.FINISHED);
        taskService.setVariableLocal(taskId, FlowableConstant.ERROR_MESSAGE, message);

        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(delegateTask.getProcessDefinitionId());
        taskEventHandler.notify(serviceName, beanName, processDefinition.getKey(), delegateTask.getProcessInstanceId(), delegateTask.getExecutionId(), taskId, delegateTask.getTaskDefinitionKey(), TaskNotifyEvent.TASK_EDN, "");
        taskService.complete(taskId);
    }

    public void pollingTask(DelegateTask delegateTask, String serviceName, String beanName, String repeat) {
        taskService.setVariableLocal(delegateTask.getId(), FlowableConstant.TASK_STATUS_KEY, ExecutionStatus.EXECUTING);
        createTimerJob(delegateTask, serviceName, beanName, repeat);
    }

    public void failedTask(DelegateTask delegateTask, String serviceName, String beanName, String errorMessage) {
        String taskId = delegateTask.getId();
        String processInstanceId = delegateTask.getProcessInstanceId();
        String executionId = delegateTask.getExecutionId();
        taskService.setVariableLocal(taskId, FlowableConstant.TASK_STATUS_KEY, ExecutionStatus.FAILED);
        taskService.setVariableLocal(taskId, FlowableConstant.ERROR_MESSAGE, errorMessage);
        taskService.setVariableLocal(taskId, FlowableConstant.ERROR_TIME, new Date());

        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(delegateTask.getProcessDefinitionId());
        String processDefinitionKey = processDefinition.getKey();
        // task中断事件
        taskEventHandler.notify(serviceName, beanName, processDefinitionKey, processInstanceId,
                executionId, taskId, delegateTask.getTaskDefinitionKey(),
                TaskNotifyEvent.TASK_SUSPEND, errorMessage);
        // 流程中断事件
        runtimeService.setVariable(executionId, FlowableConstant.PROCESS_STATUS_KEY, ProcessStatus.FAILED);
        flowableProcessRecordService.failedProcess(processInstanceId, errorMessage);
        boolean notify = processEventHandler.notify(processDefinitionKey, processInstanceId, executionId, ProcessNotifyEvent.PROCESS_SUSPEND, errorMessage);
        if (!notify) {
            processEventHandler.createTimerJobForEventRetry(delegateTask.getProcessDefinitionId(), processInstanceId, ProcessNotifyEvent.PROCESS_SUSPEND, errorMessage);
        }
        log.error("任务失败 task={}, errorMessage={}", delegateTask, errorMessage);
    }

    private void createTimerJob(DelegateTask delegateTask, String serviceName, String beanName, String repeat) {
        TimerJobEntity timerJobEntity = timerJobService.createTimerJob();
        timerJobEntity.setCreateTime(new Date());
        timerJobEntity.setExclusive(true);
        timerJobEntity.setJobHandlerType(TaskQueryTimerJobHandler.TYPE);
        timerJobEntity.setJobType(Job.JOB_TYPE_TIMER);
        timerJobEntity.setExecutionId(delegateTask.getExecutionId());
        timerJobEntity.setProcessInstanceId(delegateTask.getProcessInstanceId());
        timerJobEntity.setProcessDefinitionId(delegateTask.getProcessDefinitionId());
        String jobHandlerConfig = TimerJobHandlerUtil.createTimerJobHandlerConfig(delegateTask.getId(), serviceName, beanName);
        if (StringUtils.isBlank(jobHandlerConfig)) {
            ObjectNode cfgJson = processEngineConfiguration.getObjectMapper().createObjectNode();
            cfgJson.put(TIMER_TASK_ID, delegateTask.getId());
            cfgJson.put(TIMER_SERVICE_NAME, serviceName);
            cfgJson.put(TIMER_BEAN_NAME, beanName);
            jobHandlerConfig = cfgJson.toString();
        }
        timerJobEntity.setJobHandlerConfiguration(jobHandlerConfig);
        timerJobEntity.setRepeat(repeat);
        Clock clock = processEngine.getProcessEngineConfiguration().getClock();
        DurationBusinessCalendar businessCalendar = new DurationBusinessCalendar(clock);
        Date dueDate = businessCalendar.resolveEndDate(repeat);
        timerJobEntity.setDuedate(dueDate);
        if (CommandContextUtil.getCommandContext() == null) {
            saveTimerJob(timerJobEntity);
            return;
        }
        timerJobService.scheduleTimerJob(timerJobEntity);
    }

    private void saveTimerJob(TimerJobEntity jobEntity) {
        ActRuTimerJob timerJob = new ActRuTimerJob();
        timerJob.setRev(1);
        timerJob.setType(jobEntity.getJobType());
        timerJob.setExclusive(true);
        timerJob.setExecutionId(jobEntity.getExecutionId());
        timerJob.setProcessInstanceId(jobEntity.getProcessInstanceId());
        timerJob.setProcDefId(jobEntity.getProcessDefinitionId());
        timerJob.setScopeType(jobEntity.getScopeType());
        timerJob.setRetries(0);
        timerJob.setDuedate(jobEntity.getDuedate());
        timerJob.setRepeat(jobEntity.getRepeat());
        timerJob.setHandlerType(jobEntity.getJobHandlerType());
        timerJob.setHandlerCfg(jobEntity.getJobHandlerConfiguration());
        timerJob.setCreateTime(jobEntity.getCreateTime());
        timerJob.setTenantId(jobEntity.getTenantId());
        actRuTimerJobMapper.insert(timerJob);
    }
}
