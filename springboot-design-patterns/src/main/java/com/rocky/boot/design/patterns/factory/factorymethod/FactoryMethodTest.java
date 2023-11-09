package com.rocky.boot.design.patterns.factory.factorymethod;

/**
 * @author : rocky
 * @date : created in 2023/11/8
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        //测试者1试玩游戏A
        GameFactory gameAFactory = new GameAFactory();
        Tester tester1 = new Tester(gameAFactory);
        tester1.testGame();

        //测试者2试玩游戏B
        GameFactory gameBFactory = new GameBFactory();
        Tester tester2 = new Tester(gameBFactory);
        tester2.testGame();

        //测试者3试玩游戏C
        GameFactory gameCFactory = new GameCFactory();
        Tester tester3 = new Tester(gameCFactory);
        tester3.testGame();
    }
}
