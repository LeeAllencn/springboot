package com.rocky.boot.common.utils;

import java.math.BigDecimal;

/**
 * @author Rocky
 * Description: 浮点数的基本运算
 * 注：
 * 1.通常建议优先使用基于String参数的构造器来创建BigDecimal对象
 * 2.不要直接使用double浮点数参数的构造器来创建BigDecimal对象，同样会发生精度丢失的问题
 * 3.通过BigDecimal.valueOf(double value)静态方法来创建BigDecimal对象
 * @date Created in 13:39 2018/6/23
 */
public class FloatingOperationUtil {

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 构造器私有，让这个类不能实例化
     */
    private FloatingOperationUtil() {
    }

    /**
     * 提供精确的加法运算:add()
     *
     * @param v1 值1
     * @param v2 值2
     * @return double
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算:subtract()
     *
     * @param v1 值1
     * @param v2 值2
     * @return double
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算:multiply()
     *
     * @param v1 值1
     * @param v2 值2
     * @return double
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的除法运算:divide()
     *
     * @param v1 值1
     * @param v2 值2
     * @return double
     */
    public static double div(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
