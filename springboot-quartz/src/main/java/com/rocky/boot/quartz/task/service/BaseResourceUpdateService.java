package com.rocky.boot.quartz.task.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rocky
 * Description: 资源更新的基础服务
 * Created in 2021/3/10
 */
@Slf4j
public abstract class BaseResourceUpdateService {

    public void update() {
        log.info("{}更新任务开始执行", this.getClass().getSimpleName());
        updateData();
        log.info("{}更新任务执行结束", this.getClass().getSimpleName());
    }

    /**
     * 更新数据抽象方法
     */
    public abstract void updateData();
}
