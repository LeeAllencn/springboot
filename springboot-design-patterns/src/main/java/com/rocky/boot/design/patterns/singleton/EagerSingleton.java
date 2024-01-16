package com.rocky.boot.design.patterns.singleton;

/**
 * @author rocky
 * description: 饿汉式
 * @date Created in 2018/11/19
 */
public class EagerSingleton {
    /**
     * 类加载时就初始化
     * 在静态初始化器（static Initializer）中创建单例。这段代码保证了线程安全（thread safe）。
     */
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}
