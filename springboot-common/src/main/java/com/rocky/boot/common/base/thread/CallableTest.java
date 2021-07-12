package com.rocky.boot.common.base.thread;

import com.rocky.boot.common.utils.ThreadPoolUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : rocky
 * @date : created in 2021/7/12
 */
public class CallableTest implements Callable<Integer> {

    private final static Integer MAX = 10;

    @Override
    public Integer call() {
        int sum = 0;
        for (int i = 0; i < MAX; i ++) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName() + " return: " + sum);
        return sum;
    }

    public static void main(String[] args) {
        CallableTest callableTest = new CallableTest();
        FutureTask<Integer> futureTask = new FutureTask<>(callableTest);
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.createThreadPool(CallableTest.class);
        threadPoolExecutor.execute(futureTask);
        threadPoolExecutor.shutdown();
        try {
            System.out.println("线程返回的结果:" + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
