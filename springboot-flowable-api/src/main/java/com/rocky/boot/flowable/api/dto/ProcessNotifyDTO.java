package com.rocky.boot.flowable.api.dto;

import com.rocky.boot.flowable.api.enums.ProcessNotifyEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author : rocky
 * @date : created in 2023/4/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProcessNotifyDTO extends BaseProcessDTO {

    private ProcessNotifyEvent event;

    private String message;

    /**
     * runtimeService的变量
     */
    private Map<String, Object> variables;
}
