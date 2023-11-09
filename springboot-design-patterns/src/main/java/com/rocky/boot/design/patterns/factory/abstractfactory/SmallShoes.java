package com.rocky.boot.design.patterns.factory.abstractfactory;

/**
 * 具体产品，小号鞋子
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class SmallShoes implements Shoe {
    @Override
    public void printShone() {
        System.out.println("小号鞋子");
    }
}
