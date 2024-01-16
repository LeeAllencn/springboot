package com.rocky.boot.design.patterns.singleton;

/**
 * @author rocky
 * description:
 * @date Created in 2018/11/19
 */
public class EagerSingleton {
    /**
     * 类加载时就初始化
      */
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}
