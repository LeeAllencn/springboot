package com.rocky.boot.flowable.customer.process;

import com.rocky.boot.flowable.api.dto.ProcessNotifyDTO;

/**
 * @author : rocky
 * @date : created in 2023/4/17
 */
public interface BaseProcessHandler {
    /**
     * 流程通知接口
     * @param dto ProcessNotifyDTO
     * @throws Exception 异常
     */
    void processNotify(ProcessNotifyDTO dto) throws Exception;
}
