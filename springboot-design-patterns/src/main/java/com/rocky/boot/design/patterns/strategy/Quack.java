package com.rocky.boot.design.patterns.strategy;

/**
 * @author rocky
 * @Description:
 * @Date: Created in 2018/11/19
 */
public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
