package com.rocky.boot.common.base.thread;

import com.google.common.util.concurrent.*;
import com.rocky.boot.common.utils.ThreadPoolUtil;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ExecutorService;

/**
 * @author : rocky
 * @date : created in 2021/7/13
 */
public class ListenableFutureTest {

    public static void main(String[] args) {
        // 创建一个线程池service
        ExecutorService delegate = ThreadPoolUtil.createThreadPool(ListenableFutureTest.class);
        // 创建一个ListeningExecutorService实例
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
        // 提交一个可监听的线程
        ListenableFuture<Integer> listenableFuture = executorService.submit(new CallableTest());
        // 线程结果处理回调函数
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer integer) {
                System.out.println("ListenableFutureTest return:" + integer);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("ListenableFutureTest failed:" + throwable);
            }
        } , executorService);

        executorService.shutdown();
    }
}
