package com.rocky.boot.springbootdesignpatterns.observer;

/**
 * @author rocky
 * @Description:
 * @Date: Created in 2018/11/19
 */
public interface Subject {

    /**
     * 注册观察者
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObservers();
}
