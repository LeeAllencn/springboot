package com.rocky.boot.design.patterns.factory.abstractfactory;

/**
 * 大型鞋子工厂，生产配套的大号鞋子和鞋垫
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class CompleteLargeShoeFactory implements CompleteShoeFactory{
    @Override
    public Shoe createShoe() {
        return new LargeShoes();
    }

    @Override
    public Insole createInsole() {
        return new LargeInsole();
    }
}
