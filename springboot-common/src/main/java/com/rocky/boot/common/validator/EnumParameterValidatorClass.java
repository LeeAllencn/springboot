package com.rocky.boot.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author rocky
 * Description: 枚举参数校验器实现类
 * Created in 2021/2/5
 */
public class EnumParameterValidatorClass implements ConstraintValidator<EnumParameterValidator, Object> {

    private String values;

    @Override
    public void initialize(EnumParameterValidator constraintAnnotation) {
        this.values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Boolean flag = false;
        String[] valueArray = values.split(",");
        for (String value : valueArray) {
            if (value.equals(o)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
