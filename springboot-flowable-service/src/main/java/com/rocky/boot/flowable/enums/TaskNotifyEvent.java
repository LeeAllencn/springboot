package com.rocky.boot.flowable.enums;

/**
 * @author : rocky
 * @date : created in 2023/4/20
 */
public enum TaskNotifyEvent {
    /**
     * 任务开始
     */
    TASK_START,

    /**
     * 任务中断
     */
    TASK_SUSPEND,

    /**
     * 任务重试
     */
    TASK_RETRY,

    /**
     * 任务结束
     */
    TASK_EDN
}
