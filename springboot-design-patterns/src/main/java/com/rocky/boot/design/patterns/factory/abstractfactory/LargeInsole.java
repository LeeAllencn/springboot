package com.rocky.boot.design.patterns.factory.abstractfactory;

/**
 * 具体产品，大号鞋垫
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class LargeInsole implements Insole {
    @Override
    public void printInsole() {
        System.out.println("大号鞋垫");
    }
}
