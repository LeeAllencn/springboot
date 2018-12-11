package com.rocky.boot.springbootdesignpatterns.decorator;

/**
 * @author rocky
 * @Description: 综合咖啡
 * @Date: Created in 2018/12/11
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
