package com.rocky.boot.design.patterns.factory.abstractfactory;

/**
 * 具体产品，大号鞋子
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class LargeShoes implements Shoe {

    @Override
    public void printShone() {
        System.out.println("大号鞋子");
    }
}
