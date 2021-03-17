package com.rocky.boot.kubernetes.enums;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/16
 */
public enum WorkloadType {
    /**
     * 无状态应用
     */
    Deployment,

    /**
     * 有状态应用
     */
    StatefulSet,

    /**
     * 守护进程集
     */
    DaemonSet,

    /**
     * job
     */
    Job,

    /**
     * cronJob
     */
    CronJob
}
