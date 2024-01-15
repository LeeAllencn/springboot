package com.rocky.boot.design.patterns.strategy;

/**
 * description: 模型鸭
 * @author rocky
 * @date Created in 2018/11/19
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I am a model duck");
    }
}
