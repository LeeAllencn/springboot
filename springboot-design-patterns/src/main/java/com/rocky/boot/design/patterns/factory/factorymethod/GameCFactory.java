package com.rocky.boot.design.patterns.factory.factorymethod;

/**
 * 帮忙子类，游戏C工厂
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class GameCFactory implements GameFactory {
    @Override
    public Game createGame() {
        return new GameC();
    }
}
