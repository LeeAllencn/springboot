package com.rocky.boot.flowable.service;

import com.rocky.boot.flowable.enums.ProcessStatus;
import com.rocky.boot.flowable.model.FlowableProcessRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 流程记录信息表 服务类
 * </p>
 *
 * @author rocky
 * @since 2023-04-14
 */
public interface IFlowableProcessRecordService extends IService<FlowableProcessRecord> {

    /**
     * 更新流程实例Id
     * @param uuid 主键uuid
     * @param processInstanceId 流程实例Id
     * @return boolean
     */
    boolean updateProcessInstanceIdByUuid(String uuid, String processInstanceId);

    /**
     * 更新流程状态
     * @param processInstanceId 流程实例Id
     * @param processStatus ProcessStatus
     */
    /**
     * 更新流程状态
     * @param processInstanceId 流程实例Id
     * @param processStatus ProcessStatus
     * @return boolean
     */
    boolean updateStatusByProcessInstanceId(String processInstanceId, ProcessStatus processStatus);

    /**
     * 完成流程
     * @param processInstanceId 流程实例Id
     * @return boolean
     */
    boolean completeProcess(String processInstanceId);

    /**
     * 取消流程
     * @param processInstanceId 流程实例Id
     * @param reason 原因
     * @return boolean
     */
    boolean cancelProcess(String processInstanceId, String reason);

    /**
     * 失败流程
     * @param processInstanceId 流程实例Id
     * @param reason 原因
     * @return boolean
     */
    boolean failedProcess(String processInstanceId, String reason);
}
