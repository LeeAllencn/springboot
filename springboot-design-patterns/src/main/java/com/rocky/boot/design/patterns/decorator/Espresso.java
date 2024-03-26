package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 浓缩咖啡
 * @date Created in 2018/12/11
 */
public class Espresso extends BaseBeverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
