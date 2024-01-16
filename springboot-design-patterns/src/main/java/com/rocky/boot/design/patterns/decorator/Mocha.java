package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 摩卡调料
 * @date Created in 2018/12/11
 */
public class Mocha extends CondimentDecorator {

    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mocha";
    }

    @Override
    public double cost() {
        return 0.20 + beverage.cost();
    }
}
