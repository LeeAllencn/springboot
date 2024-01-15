package com.rocky.boot.design.patterns.strategy;

/**
 * description: 鸭子抽象类
 * 设计：
 * 设计原则：封装变化
 * 1.鸭子的fly()和quack()行为会随着鸭子的不同而改变，所以把它们从Duck类中取出来，建立一种新类来代表每个行为
 * @author rocky
 * @date Created in 2018/11/19
 */
public abstract class Duck {

    /**
     * 飞行行为
     */
    FlyBehavior flyBehavior;

    /**
     * 呱呱叫行为
     */
    QuackBehavior quackBehavior;

    public Duck() {}

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    /**
     * 显示抽象行为
     */
    public abstract void display();

    /**
     * 委托给FlyBehavior行为类
     */
    public void performFly() {
        flyBehavior.fly();
    }

    /**
     * 委托给QuackBehavior行为类
     */
    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All ducks float,even decoys!");
    }
}
