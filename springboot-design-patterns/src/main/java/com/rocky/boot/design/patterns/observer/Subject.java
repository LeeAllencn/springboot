package com.rocky.boot.design.patterns.observer;

/**
 * @author rocky
 * description: 主题接口
 * @date Created in 2018/11/19
 */
public interface Subject {

    /**
     * 注册观察者
     * @param observer 观察者
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     * @param observer 观察者
     */
    void removeObserver(Observer observer);

    /**
     * 当主题状态改变时，通知所有观察者
     */
    void notifyObservers();
}
