package com.rocky.boot.design.patterns.strategy;

/**
 * description: 呱呱叫行为接口，所有呱呱叫类都实现这个接口
 * 设计：见FlyBehavior接口
 * @author rocky
 * @date Created in 2018/11/19
 */
public interface QuackBehavior {

    /**
     * 呱呱叫方法
     */
    void quack();
}
