package com.rocky.boot.flowable.model.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author : rocky
 * @date : created in 2023/4/20
 */
@Data
public class ExecutionDTO extends BaseTaskDTO {

    private Map<String, Object> processVariables;
}
