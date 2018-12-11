package com.rocky.boot.springbootdesignpatterns.decorator;

/**
 * @author rocky
 * @Description: 饮料抽象类
 * @Date: Created in 2018/12/11
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
