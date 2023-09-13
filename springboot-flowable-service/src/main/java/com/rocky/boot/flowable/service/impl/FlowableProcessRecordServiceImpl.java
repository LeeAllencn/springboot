package com.rocky.boot.flowable.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rocky.boot.flowable.enums.ProcessStatus;
import com.rocky.boot.flowable.model.FlowableProcessRecord;
import com.rocky.boot.flowable.mapper.FlowableProcessRecordMapper;
import com.rocky.boot.flowable.service.IFlowableProcessRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 流程记录信息表 服务实现类
 * </p>
 *
 * @author rocky
 * @since 2023-04-14
 */
@Service
public class FlowableProcessRecordServiceImpl extends ServiceImpl<FlowableProcessRecordMapper, FlowableProcessRecord> implements IFlowableProcessRecordService {

    @Override
    public boolean updateProcessInstanceIdByUuid(String uuid, String processInstanceId) {
        FlowableProcessRecord record = new FlowableProcessRecord();
        record.setUuid(uuid);
        record.setProcessInstanceId(processInstanceId);
        return updateById(record);
    }

    @Override
    public boolean updateStatusByProcessInstanceId(String processInstanceId, ProcessStatus processStatus) {
        FlowableProcessRecord record = new FlowableProcessRecord();
        record.setStatus(processStatus.name());
        return update(record, Wrappers.lambdaUpdate(new FlowableProcessRecord()).eq(FlowableProcessRecord::getProcessInstanceId, processInstanceId));
    }

    @Override
    public boolean completeProcess(String processInstanceId) {
        FlowableProcessRecord record = new FlowableProcessRecord();
        record.setStatus(ProcessStatus.FINISHED.name());
        record.setActive(false);
        record.setEndTime(new Date());
        return update(record, Wrappers.lambdaUpdate(new FlowableProcessRecord()).eq(FlowableProcessRecord::getProcessInstanceId, processInstanceId));
    }

    @Override
    public boolean cancelProcess(String processInstanceId, String reason) {
        FlowableProcessRecord record = new FlowableProcessRecord();
        record.setActive(false);
        record.setEndTime(new Date());
        return update(record, Wrappers.lambdaUpdate(new FlowableProcessRecord()).eq(FlowableProcessRecord::getProcessInstanceId, processInstanceId));
    }

    @Override
    public boolean failedProcess(String processInstanceId, String reason) {
        FlowableProcessRecord record = new FlowableProcessRecord();
        record.setStatus(ProcessStatus.FAILED.name());
        record.setMessage(reason);
        record.setEndTime(new Date());
        return update(record, Wrappers.lambdaUpdate(new FlowableProcessRecord()).eq(FlowableProcessRecord::getProcessInstanceId, processInstanceId));
    }
}
