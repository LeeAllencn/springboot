package com.rocky.boot.design.patterns.observer;

/**
 * @author rocky
 * description: 扩展布告板
 * @date Created in 2018/11/19
 */
public class ThirdPartyDisplay implements Observer, DisplayElement {

    private Subject weatherData;

    public ThirdPartyDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("This is third party display!");
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        display();
    }
}
