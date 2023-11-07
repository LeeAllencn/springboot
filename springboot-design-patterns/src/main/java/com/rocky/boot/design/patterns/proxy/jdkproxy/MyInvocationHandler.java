package com.rocky.boot.design.patterns.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : rocky
 * @date : created in 2023/11/7
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        System.out.println("before invoke " + methodName);
        Object result = method.invoke(target, args);
        System.out.println("after invoke " + methodName);
        return result;
    }
}
