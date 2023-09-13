package com.rocky.boot.flowable.model.resp;

import com.rocky.boot.flowable.enums.TaskStatus;
import lombok.Data;

import java.util.Map;

/**
 * @author : rocky
 * @date : created in 2023/4/20
 */
@Data
public class TaskHandleResp {

    private TaskStatus status;

    private boolean hasError;

    private String errorMessage;

    private Map<String, Object> updateVariables;
}
