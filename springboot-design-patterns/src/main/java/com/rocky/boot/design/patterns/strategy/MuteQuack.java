package com.rocky.boot.design.patterns.strategy;

/**
 * description: 不出声，不会叫
 * @author rocky
 * @date Created in 2018/11/19
 */
public class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("<< silence >>");
    }
}
