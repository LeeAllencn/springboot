package com.rocky.boot.design.patterns.factory.abstractfactory;

/**
 * 代码测试类
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        // 购买大号鞋子
        // 这里通常使用单例模式生成工厂
        CompleteShoeFactory factory = new CompleteLargeShoeFactory();
        Customer customer = new Customer(factory);
        customer.buyCompleteShoe();
    }
}
