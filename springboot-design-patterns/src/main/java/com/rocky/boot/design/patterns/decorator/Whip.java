package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 奶泡调料
 * @date Created in 2018/12/11
 */
public class Whip extends CondimentDecorator {

    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Whip";
    }

    @Override
    public double cost() {
        return 0.10 + beverage.cost();
    }
}
