package com.rocky.boot.flowable.api.enums;

/**
 * 流程时间业务枚举定义
 * @author : rocky
 * @date : created in 2023/4/17
 */
public enum ProcessNotifyEvent {
    /**
     * 流程已启动
     */
    PROCESS_STARTED,

    /**
     * 流程完成
     */
    PROCESS_COMPLETED,

    /**
     * 流程中断
     */
    PROCESS_SUSPEND,

    /**
     * 流程删除
     */
    PROCESS_DELETE,

    /**
     * 流程重试
     */
    PROCESS_RETRY
}
