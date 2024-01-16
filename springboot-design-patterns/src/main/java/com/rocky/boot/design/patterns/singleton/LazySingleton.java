package com.rocky.boot.design.patterns.singleton;

/**
 * description: 懒汉式，线程安全
 * 缺点：
 * 同步会降低性能。一旦设置好instance变量，就不需要同步这个方法了，之后每次调用这个方法，同步都是一种累赘。
 * 如果getInstance()的性能对应用程序不是很关键，就什么也不做。但是必须知道，同步一个方法可能造成程序执行效率下降100倍。因此，如果将getInstance()的程序使用在频繁运行的地方，就得重新考虑了。
 * @author rocky
 * @date Created in 2018/11/19
 */
public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton() {}

    /**
     * 通过添加synchronized关键字到getInstance()方法中，我们迫使每个线程进入这个方法之前，要先等候别的线程离开该方法。
     * 也就是说，不会有两个线程可以同时进入这个方法。
     * @return LazySingleton
     */
    public static synchronized LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
