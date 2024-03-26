package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 摩卡调料
 * @date Created in 2018/12/11
 */
public class Mocha extends BaseCondimentDecorator {

    private BaseBeverage beverage;

    public Mocha(BaseBeverage beverage) {
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
