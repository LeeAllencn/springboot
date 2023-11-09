package com.rocky.boot.design.patterns.factory.factorymethod;

/**
 * 定义工厂(父类)
 * @author : rocky
 * @date : created in 2023/11/9
 */
public interface GameFactory {
    /**
     * 创建游戏
     * @return Game
     */
    Game createGame();
}
