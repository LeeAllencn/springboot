package com.rocky.boot.flowable.customer.process;

import com.rocky.boot.flowable.api.dto.BaseProcessDTO;
import com.rocky.boot.flowable.api.enums.ProcessNotifyEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : rocky
 * @date : created in 2023/4/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProcessNotifyAdapterDTO<T> extends BaseProcessDTO {
    private T variables;

    private ProcessNotifyEvent event;

    private String message;
}
