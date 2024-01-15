package com.rocky.boot.design.patterns.strategy;

/**
 * description: 实现了所有火箭动力的飞行动作
 * @author rocky
 * @date Created in 2018/11/19
 */
public class FlyRocketPowered implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I am flying with a rocket!");
    }
}
