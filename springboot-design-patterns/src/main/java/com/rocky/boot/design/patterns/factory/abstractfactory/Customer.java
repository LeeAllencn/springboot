package com.rocky.boot.design.patterns.factory.abstractfactory;

/**
 * 客户类，购买鞋子
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class Customer {

    private final CompleteShoeFactory factory;

    public Customer(CompleteShoeFactory factory) {
        this.factory = factory;
    }

    /**
     * 购买完整的鞋
     */
    public void buyCompleteShoe() {
        Shoe myShoe = factory.createShoe();
        myShoe.printShone();

        Insole myInsole = factory.createInsole();
        myInsole.printInsole();

        System.out.println("我已经买了配套产品，终于有鞋穿了！");
    }
}
