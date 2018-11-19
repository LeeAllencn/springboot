package com.rocky.boot.springbootdesignpatterns.strategy;

/**
 * @author rocky
 * @Description:
 * @Date: Created in 2018/11/19
 */
public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I am flying!!");
    }
}
