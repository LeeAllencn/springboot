package com.rocky.boot.flowable.model.dto;

import com.rocky.boot.flowable.api.dto.BaseProcessDTO;
import lombok.Data;

/**
 * @author : rocky
 * @date : created in 2023/4/20
 */
@Data
public class BaseTaskDTO extends BaseProcessDTO {
    private String taskId;

    private String taskKey;

    private String executionId;

    private String operator;
}
