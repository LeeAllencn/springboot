package com.rocky.boot.design.patterns.proxy.jdkproxy;

/**
 * @author : rocky
 * @date : created in 2023/11/7
 */
public class HelloServiceImpl implements IHelloService {
    @Override
    public String hello() {
        System.out.println("执行hello()的实际对象是：" + this);
        return "HelloServiceImpl.hello";
    }
}
