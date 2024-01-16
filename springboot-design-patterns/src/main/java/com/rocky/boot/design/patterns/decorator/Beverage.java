package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 饮料抽象类
 * @date Created in 2018/12/11
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
