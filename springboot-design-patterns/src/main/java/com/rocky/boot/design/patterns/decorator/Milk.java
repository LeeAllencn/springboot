package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * @Description: 牛奶调料
 * @Date: Created in 2018/12/11
 */
public class Milk extends CondimentDecorator {

    private Beverage beverage;

    public Milk(Beverage beverage) {
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
