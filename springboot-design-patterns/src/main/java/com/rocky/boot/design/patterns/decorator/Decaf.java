package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 低咖啡因咖啡
 * @date Created in 2018/12/11
 */
public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf Coffee";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
