package com.rocky.boot.design.patterns.singleton;

/**
 * @author rocky
 * description: 双重检验锁
 * @date Created in 2018/11/19
 */
public class DoubleCheckLockSingleton {
    /**
     * 声明成 volatile
     * volatile关键词确保，当instance变量被初始化成Singleton示例时，多个线程正确的处理instance变量。
     */
    private volatile static DoubleCheckLockSingleton instance = null;

    private DoubleCheckLockSingleton() {}

    public static DoubleCheckLockSingleton getInstance() {
        // 在getInstance()中减少使用同步，大大减少getInstance()的时间消耗
        if(instance == null) {
            synchronized(DoubleCheckLockSingleton.class) {
                if(instance == null) {
                    instance = new DoubleCheckLockSingleton();
                }
            }
        }
        return instance;
    }
}
