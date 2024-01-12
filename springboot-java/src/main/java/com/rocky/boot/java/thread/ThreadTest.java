package com.rocky.boot.java.thread;

/**
 * @author : rocky
 * @date : created in 2021/7/12
 */
public class ThreadTest extends Thread {

    private final static Integer MAX = 10;

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < MAX; i ++) {
            sum += i;
        }
        System.out.println(this.getName() + " return: " + sum);
    }

    public static void main(String[] args) {
        ThreadTest thread = new ThreadTest();
        thread.start();
    }
}
