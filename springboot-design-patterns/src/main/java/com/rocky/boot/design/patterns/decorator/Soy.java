package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 豆浆调料
 * @date Created in 2018/12/11
 */
public class Soy extends BaseCondimentDecorator {

    private BaseBeverage beverage;

    public Soy(BaseBeverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Soy";
    }

    @Override
    public double cost() {
        return 0.15 + beverage.cost();
    }
}
