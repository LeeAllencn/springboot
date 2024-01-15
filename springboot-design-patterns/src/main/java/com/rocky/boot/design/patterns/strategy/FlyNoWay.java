package com.rocky.boot.design.patterns.strategy;

/**
 * description: 实现了所有不会飞的动作
 * @author rocky
 * @date Created in 2018/11/19
 */
public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I can not fly!!");
    }
}
