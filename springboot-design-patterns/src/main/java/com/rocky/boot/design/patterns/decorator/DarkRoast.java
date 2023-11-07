package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * @Description: 深焙咖啡
 * @Date: Created in 2018/12/11
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast Coffee";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}
