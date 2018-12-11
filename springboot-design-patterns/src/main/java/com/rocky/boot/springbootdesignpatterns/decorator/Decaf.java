package com.rocky.boot.springbootdesignpatterns.decorator;

/**
 * @author rocky
 * @Description: 低咖啡因咖啡
 * @Date: Created in 2018/12/11
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
