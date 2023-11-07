package com.rocky.boot.design.patterns.proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @author : rocky
 * @date : created in 2023/11/7
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        Object proxyObject = Proxy.newProxyInstance(helloService.getClass().getClassLoader(),
                helloService.getClass().getInterfaces(),
                new MyInvocationHandler(helloService));
        System.out.println(proxyObject.getClass());
        String result = ((IHelloService)proxyObject).hello();
        System.out.println(result);
    }
}
