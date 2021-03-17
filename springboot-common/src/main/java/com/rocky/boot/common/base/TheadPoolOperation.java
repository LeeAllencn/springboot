package com.rocky.boot.common.base;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rocky
 * Description: 线程池操作
 * Created in 2021/3/17
 */
public class TheadPoolOperation {

    public static void main(String[] args) {

        TheadPoolOperation theadPoolOperation = new TheadPoolOperation();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new NamedThreadFactory(theadPoolOperation.getClass().getSimpleName() + "-thread-task-", false),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        // 任务1
        threadPoolExecutor.execute(() -> {
            try {
                Thread.sleep(3 * 1000);
                System.out.println("--helloThreadPool_001--" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 任务2
        threadPoolExecutor.execute(() -> System.out.println("--helloThreadPool_002--" + Thread.currentThread().getName()));

        threadPoolExecutor.shutdown();
    }
}
