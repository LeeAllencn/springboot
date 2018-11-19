package com.rocky.boot.springbootdesignpatterns.observer;

/**
 * @author rocky
 * @Description: 扩展布告板
 * @Date: Created in 2018/11/19
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
