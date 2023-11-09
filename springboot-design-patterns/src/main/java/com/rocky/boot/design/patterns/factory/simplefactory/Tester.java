package com.rocky.boot.design.patterns.factory.simplefactory;

/**
 * 测试者(客户)类
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class Tester {

    private char type;
    public Tester(char type) {
        this.type = type;
    }
    public void testGame() {
        GameFactory gameFactory = new GameFactory();
        //通过简单工厂获取游戏实例
        Game game = gameFactory.createGame(type);
        //试玩游戏
        game.play();
    }
}
