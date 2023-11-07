package com.rocky.boot.design.patterns.strategy;

/**
 * @author rocky
 * @Description:
 * @Date: Created in 2018/11/19
 */
public class FlyRocketPowered implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I am flying with a rocket!");
    }
}
