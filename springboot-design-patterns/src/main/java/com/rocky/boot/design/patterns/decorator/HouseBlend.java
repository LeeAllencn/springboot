package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 综合咖啡
 * @date Created in 2018/12/11
 */
public class HouseBlend extends BaseBeverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
