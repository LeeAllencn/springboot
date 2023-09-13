package com.rocky.boot.flowable.constant;

/**
 * @author : rocky
 * @date : created in 2023/4/14
 */
public interface FlowableConstant {

    String FLOWABLE_PROCESS_RECORD_UUID_KEY = "FLOWABLE_PROCESS_RECORD_UUID_KEY";

    String SERVICE_REF_KEY = "serviceRef";

    String NOTIFY_PRE_KEY = "NOTIFY:";

    String NOTIFY_STATUS_SUFFIX_KEY = ":STATUS";

    String NOTIFY_MESSAGE_SUFFIX_KEY = ":MESSAGE";

    /**
     * 任务执行状态key
     */
    String TASK_STATUS_KEY = "TASK_STATUS_KEY";

    /**
     * 执行用户
     */
    String EXECUTION_SYSTEM_USER = "systemUser";
    String ERROR_MESSAGE = "ERROR_MESSAGE";
    String ERROR_TIME = "ERROR_TIME";
    String PROCESS_STATUS_KEY = "PROCESS_STATUS_KEY";
}
