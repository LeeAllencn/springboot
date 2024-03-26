package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 深焙咖啡
 * @date Created in 2018/12/11
 */
public class DarkRoast extends BaseBeverage {

    public DarkRoast() {
        description = "DarkRoast Coffee";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}
