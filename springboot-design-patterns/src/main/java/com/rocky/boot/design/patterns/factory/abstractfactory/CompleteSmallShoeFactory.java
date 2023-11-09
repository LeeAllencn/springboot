package com.rocky.boot.design.patterns.factory.abstractfactory;

/**
 * 小号鞋子工厂，生产配套的小号鞋子和鞋垫
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class CompleteSmallShoeFactory implements CompleteShoeFactory {
    @Override
    public Shoe createShoe() {
        return new SmallShoes();
    }

    @Override
    public Insole createInsole() {
        return new SmallInsole();
    }
}
