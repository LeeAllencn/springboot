package com.rocky.boot.springbootdesignpatterns.singleton;

/**
 * @author rocky
 * @Description:
 * @Date: Created in 2018/11/19
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
