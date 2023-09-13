package com.rocky.boot.flowable.model.dto;

import com.rocky.boot.flowable.enums.TaskNotifyEvent;
import lombok.Data;

import java.util.Map;

/**
 * @author : rocky
 * @date : created in 2023/4/20
 */
@Data
public class TaskNotifyDTO extends BaseTaskDTO {
    /**
     * 流程变量
     */
    private Map<String, Object> processVariables;
    private TaskNotifyEvent event;
    private String message;
}
