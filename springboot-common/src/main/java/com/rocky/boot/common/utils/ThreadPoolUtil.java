package com.rocky.boot.common.utils;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * 线程池工具
 * @author : rocky
 * @date : created in 2021/7/12
 */
public class ThreadPoolUtil {

    private final static int CORE_POOL_SIZE = 2;
    private final static int MAXIMUM_POOL_SIZE = 3;
    private final static long KEEP_ALIVE_TIME = 30;
    private final static int CAPACITY = 1;

    public static <T> ThreadPoolExecutor createThreadPool(Class<T> clazz) {

        return new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(CAPACITY),
                new NamedThreadFactory(clazz.getSimpleName() + "-thread-task-", false),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
