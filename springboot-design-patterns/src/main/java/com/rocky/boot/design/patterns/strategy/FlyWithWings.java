package com.rocky.boot.design.patterns.strategy;

/**
 * description: 实现了所有有翅膀的飞行动作
 * 设计：
 * 飞行动作可以被其他对象复用，因为该行为已经与鸭子类无关了
 * 即使新增一些行为，不会影响的既有的行为类，也不会影响“使用”到飞行行为的鸭子类
 * FlyBehavior和QuackBehavior行为接口的实现类都是这样的
 * @author rocky
 * @date Created in 2018/11/19
 */
public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I am flying!!");
    }
}
