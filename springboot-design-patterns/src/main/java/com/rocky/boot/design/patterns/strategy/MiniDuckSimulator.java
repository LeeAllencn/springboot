package com.rocky.boot.design.patterns.strategy;

/**
 * description: 测试类
 * @author rocky
 * @date Created in 2018/11/19
 */
public class MiniDuckSimulator {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        // 动态改变模型鸭的飞行行为
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
