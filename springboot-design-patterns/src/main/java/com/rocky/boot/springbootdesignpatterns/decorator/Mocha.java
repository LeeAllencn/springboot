package com.rocky.boot.springbootdesignpatterns.decorator;

/**
 * @author rocky
 * @Description: 摩卡调料
 * @Date: Created in 2018/12/11
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
