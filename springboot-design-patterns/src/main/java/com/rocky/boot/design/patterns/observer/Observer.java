package com.rocky.boot.design.patterns.observer;

/**
 * @author rocky
 * description: 观察者接口
 * @date Created in 2018/11/19
 */
public interface Observer {

    /**
     * 观察者更新信息
     * @param temperature 温度
     * @param humidity 湿度
     * @param pressure 压力
     */
    void update(float temperature, float humidity, float pressure);
}
