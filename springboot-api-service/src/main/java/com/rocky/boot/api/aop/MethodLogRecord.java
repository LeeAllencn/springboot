package com.rocky.boot.api.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rocky
 * Description: 方法日志记录切面注解
 * Created in 2021/3/11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodLogRecord {

    String key() default "";
}
