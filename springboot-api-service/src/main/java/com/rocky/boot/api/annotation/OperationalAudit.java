package com.rocky.boot.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : rocky
 * @date : created in 2021/9/5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationalAudit {

    // 操作
    String operate() default "";

    // 资源ID
    String resourceId() default "";

    // 资源名称
    String resourceName() default "";

    // 请求参数类型
    String paramType() default "";

    // 查询类
    String queryClass() default "";
}
