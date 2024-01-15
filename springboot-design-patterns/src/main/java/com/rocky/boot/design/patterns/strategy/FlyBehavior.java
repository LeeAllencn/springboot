package com.rocky.boot.design.patterns.strategy;

/**
 * description: 飞行行为接口，所有飞行类都实现这个接口
 * 设计：
 * 设计原则：针对接口编程，不针对实现编程
 * 鸭子的子类将使用FlyBehavior接口所表示的行为，所以实际的“实现”不会被绑死在鸭子的子类中
 * 换句话说，特定的具体行为编写在实现了FlyBehavior接口的类中
 * @author rocky
 * @date Created in 2018/11/19
 */
public interface FlyBehavior {

    /**
     * 飞行方法
     */
    void fly();
}
