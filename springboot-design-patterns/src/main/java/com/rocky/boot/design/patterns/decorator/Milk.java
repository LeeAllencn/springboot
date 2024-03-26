package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 牛奶调料
 * @date Created in 2018/12/11
 */
public class Milk extends BaseCondimentDecorator {

    private BaseBeverage beverage;

    public Milk(BaseBeverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Milk";
    }

    @Override
    public double cost() {
        return 0.10 + beverage.cost();
    }
}
