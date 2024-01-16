package com.rocky.boot.design.patterns.singleton;

/**
 * @author rocky
 * description: 静态内部类
 * @date Created in 2018/11/19
 */
public class StaticInnerClassSingleton {

    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton() {}

    public static final StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
