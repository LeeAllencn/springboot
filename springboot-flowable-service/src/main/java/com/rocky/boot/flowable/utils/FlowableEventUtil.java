package com.rocky.boot.flowable.utils;

import com.rocky.boot.flowable.api.enums.ProcessNotifyEvent;
import com.rocky.boot.flowable.constant.FlowableConstant;
import com.rocky.boot.flowable.enums.TaskNotifyEvent;

/**
 * @author : rocky
 * @date : created in 2023/4/17
 */
public class FlowableEventUtil {
    public static String getProcessEventStatusKey(ProcessNotifyEvent event) {
        return FlowableConstant.NOTIFY_PRE_KEY + event.name() + FlowableConstant.NOTIFY_STATUS_SUFFIX_KEY;
    }

    public static String getProcessEventMessageKey(ProcessNotifyEvent event) {
        return FlowableConstant.NOTIFY_PRE_KEY + event.name() + FlowableConstant.NOTIFY_MESSAGE_SUFFIX_KEY;
    }

    public static String getTaskEventStatusKey(TaskNotifyEvent event) {
        return FlowableConstant.NOTIFY_PRE_KEY + event.name() + FlowableConstant.NOTIFY_STATUS_SUFFIX_KEY;
    }

    public static String getTaskEventMessageKey(TaskNotifyEvent event) {
        return FlowableConstant.NOTIFY_PRE_KEY + event.name() + FlowableConstant.NOTIFY_MESSAGE_SUFFIX_KEY;
    }
}
