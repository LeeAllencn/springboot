package com.rocky.boot.design.patterns.factory.factorymethod;

/**
 * 帮忙子类，游戏A工厂
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class GameAFactory implements GameFactory {
    @Override
    public Game createGame() {
        return new GameA();
    }
}
