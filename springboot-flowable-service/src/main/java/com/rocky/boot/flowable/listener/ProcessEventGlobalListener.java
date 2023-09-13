package com.rocky.boot.flowable.listener;

import com.rocky.boot.flowable.api.enums.ProcessNotifyEvent;
import com.rocky.boot.flowable.constant.FlowableConstant;
import com.rocky.boot.flowable.enums.ProcessStatus;
import com.rocky.boot.flowable.handler.ProcessEventHandler;
import com.rocky.boot.flowable.service.IFlowableProcessRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.flowable.common.engine.api.delegate.event.AbstractFlowableEventListener;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventType;
import org.flowable.common.engine.impl.event.FlowableEngineEventImpl;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.history.ProcessInstanceHistoryLog;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.job.service.TimerJobService;
import org.flowable.job.service.impl.persistence.entity.TimerJobEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : rocky
 * @date : created in 2023/4/14
 * 流程全局事件监听器
 */
@Slf4j
@Component
public class ProcessEventGlobalListener extends AbstractFlowableEventListener {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TimerJobService timerJobService;

    @Resource
    private HistoryService historyService;

    @Resource
    private ProcessEventHandler processEventHandler;

    @Resource
    private IFlowableProcessRecordService flowableProcessRecordService;

    @Override
    public void onEvent(FlowableEvent event) {
        FlowableEngineEventImpl engineEvent = (FlowableEngineEventImpl) event;
        String processDefinitionId = engineEvent.getProcessDefinitionId();
        String processInstanceId = engineEvent.getProcessInstanceId();
        String executionId = engineEvent.getExecutionId();
        FlowableEventType type = event.getType();
        log.info("开始执行流程全局事件监听器 processDefinitionId={},processInstanceId={},type={}", processDefinitionId, processInstanceId, type);
        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
        String processDefinitionKey = processDefinition.getKey();
        if (FlowableEngineEventType.PROCESS_STARTED.equals(type)) {
            log.info("流程实例已经启动。在启动之前创建的流程时分发。");
            // 获取flowable_process_record表记录的uuid
            String uuid = (String) runtimeService.getVariable(processInstanceId, FlowableConstant.FLOWABLE_PROCESS_RECORD_UUID_KEY);
            // 更新库表信息
            flowableProcessRecordService.updateProcessInstanceIdByUuid(uuid, processInstanceId);
            flowableProcessRecordService.updateStatusByProcessInstanceId(processInstanceId, ProcessStatus.EXECUTING);
            // 设置流程超时时间
            processEventHandler.addProcessTimeoutTimer(processDefinitionId, processInstanceId);
            // 发送流程通知
            processEventHandler.notify(processDefinitionKey,
                    processInstanceId,
                    executionId,
                    ProcessNotifyEvent.PROCESS_STARTED, "");
        } else if (FlowableEngineEventType.PROCESS_COMPLETED.equals(type)) {
            log.info("流程实例已经完成。在最后一个节点的 ACTIVITY_COMPLETED  事件后分发。");
            // 更新库表信息
            flowableProcessRecordService.completeProcess(processInstanceId);
            // 删除流程计时器作业
            List<TimerJobEntity> timerJobEntityList = timerJobService.findTimerJobsByProcessInstanceId(processInstanceId);
            if (CollectionUtils.isNotEmpty(timerJobEntityList)) {
                timerJobEntityList.forEach(item -> timerJobService.deleteTimerJob(item));
            }
            // 发送流程通知
            boolean notify = processEventHandler.notify(processDefinitionKey,
                    processInstanceId,
                    executionId,
                    ProcessNotifyEvent.PROCESS_COMPLETED, "");
            // 如果通知失败，补偿通知
            if (!notify) {
                processEventHandler.createTimerJobForEventRetry(processDefinitionId, processInstanceId, ProcessNotifyEvent.PROCESS_COMPLETED, "");
            }
        } else if (FlowableEngineEventType.PROCESS_CANCELLED.equals(type)) {
            log.info("流程已经被取消。在流程实例从运行时中删除前分发。");
            // 更新库表信息
            ProcessInstanceHistoryLog processInstanceHistoryLog = historyService.createProcessInstanceHistoryLogQuery(processInstanceId).singleResult();
            String deleteReason = null;
            if (processInstanceHistoryLog != null) {
                deleteReason = processInstanceHistoryLog.getDeleteReason();
            }
            flowableProcessRecordService.cancelProcess(processInstanceId, deleteReason);
            // 删除流程计时器作业
            List<TimerJobEntity> timerJobEntityList = timerJobService.findTimerJobsByProcessInstanceId(processInstanceId);
            if (CollectionUtils.isNotEmpty(timerJobEntityList)) {
                timerJobEntityList.forEach(item -> timerJobService.deleteTimerJob(item));
            }
            // 发送流程通知
            processEventHandler.notify(processDefinitionKey,
                    processInstanceId,
                    executionId,
                    ProcessNotifyEvent.PROCESS_DELETE, "");
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
