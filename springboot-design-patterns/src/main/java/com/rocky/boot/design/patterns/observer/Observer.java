package com.rocky.boot.design.patterns.observer;

/**
 * @author rocky
 * @Description:
 * @Date: Created in 2018/11/19
 */
public interface Observer {

    /**
     * 观察者更新信息
     * @param temperature
     * @param humidity
     * @param pressure
     */
    void update(float temperature, float humidity, float pressure);
}
