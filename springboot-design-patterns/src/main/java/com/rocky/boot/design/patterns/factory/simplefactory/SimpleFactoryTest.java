package com.rocky.boot.design.patterns.factory.simplefactory;

/**
 * 代码测试
 * @author : rocky
 * @date : created in 2023/11/8
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        //要求测试者1试玩游戏A
        Tester tester1 = new Tester('A');
        tester1.testGame();

        //要求测试者2试玩游戏B
        Tester tester2 = new Tester('B');
        tester2.testGame();

        //要求测试者3试玩游戏C
        Tester tester3 = new Tester('C');
        tester3.testGame();
    }
}
