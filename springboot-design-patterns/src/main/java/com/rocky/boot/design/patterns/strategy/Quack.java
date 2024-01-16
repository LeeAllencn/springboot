package com.rocky.boot.design.patterns.strategy;

/**
 * description: 呱呱叫
 * @author rocky
 * @date Created in 2018/11/19
 */
public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
