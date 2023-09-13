package com.rocky.boot.flowable.handler;

import com.rocky.boot.flowable.api.dto.ProcessNotifyDTO;
import com.rocky.boot.flowable.api.enums.ProcessNotifyEvent;
import com.rocky.boot.flowable.config.FlowableServiceProp;
import com.rocky.boot.flowable.constant.FlowableConstant;
import com.rocky.boot.flowable.enums.EventStatus;
import com.rocky.boot.flowable.jobexecutor.ProcessDeleteTimerJobHandler;
import com.rocky.boot.flowable.jobexecutor.ProcessNotifyRetryTimerJobHandler;
import com.rocky.boot.flowable.remote.ProcessNotifyClientService;
import com.rocky.boot.flowable.utils.FeignClientServiceUtil;
import com.rocky.boot.flowable.utils.FlowableEventUtil;
import com.rocky.boot.flowable.utils.TimerJobHandlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.impl.calendar.DurationBusinessCalendar;
import org.flowable.common.engine.impl.runtime.Clock;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.DataObject;
import org.flowable.job.api.Job;
import org.flowable.job.service.TimerJobService;
import org.flowable.job.service.impl.persistence.entity.TimerJobEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author : rocky
 * @date : created in 2023/4/17
 */
@Slf4j
@Component
public class ProcessEventHandler {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TimerJobService timerJobService;

    @Resource
    private ProcessEngine processEngine;

    @Resource
    private FlowableServiceProp flowableServiceProp;

    @Resource
    private FeignClientServiceUtil feignClientServiceUtil;


    public boolean notify(String processDefinitionKey,
                          String processInstanceId,
                          String executionId,
                          ProcessNotifyEvent event,
                          String message) {

        DataObject serviceRefDataObject = runtimeService.getDataObject(processInstanceId, FlowableConstant.SERVICE_REF_KEY);
        if (serviceRefDataObject == null) {
            log.warn("未做serviceRef配置，processInstanceId={}", processInstanceId);
            return true;
        }
        String serviceRef = String.valueOf(serviceRefDataObject.getValue());
        try {
            String[] split = serviceRef.split(":");
            String serviceName = split[0];
            String beanName = split[1];
            ProcessNotifyClientService processNotifyRemoteService = feignClientServiceUtil.getRemoteService(serviceName, ProcessNotifyClientService.class);
            ProcessNotifyDTO dto = new ProcessNotifyDTO();
            dto.setProcessInstanceId(processInstanceId);
            dto.setExecutionId(executionId);
            dto.setProcessDefinitionKey(processDefinitionKey);
            dto.setEvent(event);
            dto.setMessage(message);
            dto.setVariables(runtimeService.getVariables(executionId));
            processNotifyRemoteService.notify(beanName, dto);
            runtimeService.setVariable(executionId, FlowableEventUtil.getProcessEventStatusKey(event), EventStatus.SUCCESS);
            return true;
        } catch (Exception e) {
            log.error("流程通知失败：serviceRef={}，processInstanceId={}，processDefinitionKey={}，event={}",
                    serviceRef, processInstanceId, processDefinitionKey, event);
            runtimeService.setVariable(executionId, FlowableEventUtil.getProcessEventStatusKey(event), EventStatus.FAILED);
            runtimeService.setVariable(executionId, FlowableEventUtil.getProcessEventMessageKey(event), StringUtils.abbreviate(e.getMessage(), 500));
            return false;
        }
    }

    public void addProcessTimeoutTimer(String processDefinitionId, String processInstanceId) {
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        Process mainProcess = bpmnModel.getMainProcess();
        String dueDate = mainProcess.getAttributeValue("http://flowable.org/bpmn", "dueDate");
        if (StringUtils.isBlank(dueDate)) {
            dueDate = flowableServiceProp.getProcessTimeoutRepeat();
        }
        createTimerJob(processDefinitionId, processInstanceId, dueDate);
    }

    private void createTimerJob(String processDefinitionId, String processInstanceId, String repeat) {
        TimerJobEntity timerJob = timerJobService.createTimerJob();
        timerJob.setCreateTime(new Date());
        timerJob.setExclusive(true);
        timerJob.setJobHandlerType(ProcessDeleteTimerJobHandler.TYPE);
        timerJob.setJobType(Job.JOB_TYPE_TIMER);
        timerJob.setExecutionId(processInstanceId);
        timerJob.setProcessInstanceId(processInstanceId);
        timerJob.setProcessDefinitionId(processDefinitionId);
        timerJob.setRepeat(repeat);
        Clock clock = processEngine.getProcessEngineConfiguration().getClock();
        DurationBusinessCalendar businessCalendar = new DurationBusinessCalendar(clock);
        Date dueDate = businessCalendar.resolveEndDate(repeat);
        timerJob.setDuedate(dueDate);
        timerJobService.scheduleTimerJob(timerJob);
    }

    public void createTimerJobForEventRetry(String processDefinitionId, String processInstanceId, ProcessNotifyEvent event, String message) {
        TimerJobEntity timerJob = timerJobService.createTimerJob();
        timerJob.setCreateTime(new Date());
        timerJob.setExclusive(true);
        timerJob.setJobHandlerType(ProcessNotifyRetryTimerJobHandler.TYPE);
        timerJob.setJobHandlerConfiguration(TimerJobHandlerUtil.createJobHandlerConfiguration(event.name(), message));
        timerJob.setJobType(Job.JOB_TYPE_TIMER);
        timerJob.setExecutionId(processInstanceId);
        timerJob.setProcessInstanceId(processInstanceId);
        timerJob.setProcessDefinitionId(processDefinitionId);
        timerJob.setRepeat(flowableServiceProp.getProcessEventRetryRepeat());
        Clock clock = processEngine.getProcessEngineConfiguration().getClock();
        DurationBusinessCalendar businessCalendar = new DurationBusinessCalendar(clock);
        Date dueDate = businessCalendar.resolveEndDate(flowableServiceProp.getProcessEventRetryRepeat());
        timerJob.setDuedate(dueDate);
        timerJobService.scheduleTimerJob(timerJob);
    }
}
