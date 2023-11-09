package com.rocky.boot.design.patterns.factory.simplefactory;

/**
 * @author : rocky
 * @date : created in 2023/11/9
 */
public class GameFactory {

    public Game createGame(char type) {
        switch (type) {
            case 'A':
                return new GameA();

            case 'B':
                return new GameB();

            case 'C':
                return new GameC();
            default:
                return null;
        }
    }
}
