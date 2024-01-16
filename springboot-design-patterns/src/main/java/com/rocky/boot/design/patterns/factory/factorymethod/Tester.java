package com.rocky.boot.design.patterns.factory.factorymethod;

/**
 * 测试者(客户)类
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class Tester {
    private final GameFactory gameFactory;

    public Tester(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public void testGame() {
        //通过工厂获取游戏实例
        Game game = gameFactory.createGame();
        //试玩游戏
        game.play();
    }
}
