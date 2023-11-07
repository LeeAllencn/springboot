package com.rocky.boot.design.patterns.proxy.cglibproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : rocky
 * @date : created in 2023/11/7
 */
public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before(method.getName());
        Object result = methodProxy.invokeSuper(o, objects);
        after(method.getName());
        return result;
    }

    /**
     * 调用invoke方法之前执行
     */
    private void before(String methodName) {
        System.out.println("调用方法" + methodName +"之【前】的日志处理");
    }

    /**
     * 调用invoke方法之后执行
     */
    private void after(String methodName) {
        System.out.println("调用方法" + methodName +"之【后】的日志处理");
    }
}
