package com.rocky.boot.java.thread;

import com.rocky.boot.common.util.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : rocky
 * @date : created in 2021/7/12
 */
public class RunnableTest implements Runnable {

    private final static Integer MAX = 10;

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < MAX; i ++) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName() + " return: " + sum);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.createThreadPool(RunnableTest.class);
        threadPoolExecutor.execute(new RunnableTest());
        threadPoolExecutor.shutdown();
    }
}
