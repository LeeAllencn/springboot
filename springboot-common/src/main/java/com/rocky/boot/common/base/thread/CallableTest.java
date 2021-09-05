package com.rocky.boot.common.base.thread;

import com.google.common.util.concurrent.*;
import com.rocky.boot.common.utils.ThreadPoolUtil;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

/**
 * @author : rocky
 * @date : created in 2021/7/12
 */
public class CallableTest implements Callable<Integer> {

    private final static Integer MAX = 10;

    @Override
    public Integer call() throws InterruptedException {
        int sum = 0;
        for (int i = 0; i < MAX; i ++) {
            sum += i;
        }
        Thread.sleep(5 * 1000);
        System.out.println(Thread.currentThread().getName() + " return: " + sum);
        System.out.println("任" + System.currentTimeMillis());
        return sum;
    }

    public static void main(String[] args) {

        // 使用方式一：FutureTask
//        CallableTest callableTest = new CallableTest();
//        FutureTask<Integer> futureTask = new FutureTask<>(callableTest);
//        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.createThreadPool(CallableTest.class);
//        threadPoolExecutor.execute(futureTask);
//        threadPoolExecutor.shutdown();
//        try {
//            System.out.println("线程返回的结果:" + futureTask.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        // 使用方式二：ListenableFuture
        // 创建一个线程池service
        ExecutorService delegate = ThreadPoolUtil.createThreadPool(CallableTest.class);
        // 创建一个ListeningExecutorService实例
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
        // 提交一个可监听的线程
        System.out.println("前" + System.currentTimeMillis());
        ListenableFuture<Integer> listenableFuture = executorService.submit(new CallableTest());
        // 线程结果处理回调函数
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {

            @Override
            public void onSuccess(@Nullable Integer result) {
                System.out.println("ListenableFutureTest return:" + result);
            }

            @Override
            public void onFailure(@NotNull Throwable t) {
                System.out.println("ListenableFutureTest failed:" + t.getMessage());
            }
        } , executorService);

        System.out.println("后" + System.currentTimeMillis());
        System.out.println("任务结束");
    }
}
