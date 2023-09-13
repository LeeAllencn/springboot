package com.rocky.boot.flowable.api.dto;

import lombok.Data;

/**
 * @author : rocky
 * @date : created in 2023/4/17
 */
@Data
public class BaseProcessDTO {
    private String processInstanceId;
    private String executionId;
    private String processDefinitionKey;
}
