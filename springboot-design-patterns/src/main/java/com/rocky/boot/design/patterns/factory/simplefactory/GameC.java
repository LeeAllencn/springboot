package com.rocky.boot.design.patterns.factory.simplefactory;

/**
 * C游戏
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class GameC implements Game {
    @Override
    public void play() {
        System.out.println("Playing GameC");
    }
}
