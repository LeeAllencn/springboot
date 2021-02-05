package com.rocky.boot.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author rocky
 * Description: 自定义注解：枚举参数校验器
 * Created in 2021/2/5
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = EnumParameterValidatorClass.class)
public @interface EnumParameterValidator {

    // 枚举常量的有效值多个使用','隔开
    String values();

    //提示内容
    String message() default "传参不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
