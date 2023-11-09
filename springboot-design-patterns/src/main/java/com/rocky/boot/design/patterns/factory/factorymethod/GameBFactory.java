package com.rocky.boot.design.patterns.factory.factorymethod;

/**
 * 帮忙子类，游戏B工厂
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class GameBFactory implements GameFactory {
    @Override
    public Game createGame() {
        return new GameB();
    }
}
