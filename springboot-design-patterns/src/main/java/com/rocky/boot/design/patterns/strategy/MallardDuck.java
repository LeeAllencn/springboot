package com.rocky.boot.design.patterns.strategy;

/**
 * description: 绿头鸭
 * @author rocky
 * @date Created in 2018/11/19
 */
public class MallardDuck extends BaseDuck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I am a real Mallard duck");
    }
}
