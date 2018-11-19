package com.rocky.boot.springbootdesignpatterns.singleton;

/**
 * @author rocky
 * @Description:
 * @Date: Created in 2018/11/19
 */
public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton() {}

    public static synchronized LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
