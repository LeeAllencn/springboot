package com.rocky.boot.design.patterns.strategy;

/**
 * description: 吱吱叫
 * @author rocky
 * @date Created in 2018/11/19
 */
public class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
