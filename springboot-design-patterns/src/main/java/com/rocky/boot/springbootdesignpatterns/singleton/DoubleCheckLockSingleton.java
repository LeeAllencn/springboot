package com.rocky.boot.springbootdesignpatterns.singleton;

/**
 * @author rocky
 * @Description:
 * @Date: Created in 2018/11/19
 */
public class DoubleCheckLockSingleton {
    /**
     * 声明成 volatile
     */
    private volatile static DoubleCheckLockSingleton instance = null;

    private DoubleCheckLockSingleton() {}

    public static DoubleCheckLockSingleton getInstance() {
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
