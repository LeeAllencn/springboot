package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 饮料抽象类
 * @date Created in 2018/12/11
 */
public abstract class BaseBeverage {

    /**
     * 描述饮料
     */
    String description = "Unknown Beverage";

    /**
     * 返回描述
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * 花费抽象方法
     * @return double
     */
    public abstract double cost();
}
