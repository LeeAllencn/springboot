package com.rocky.boot.design.patterns.observer;

/**
 * @author rocky
 * @Description: 预测布告板
 * @Date: Created in 2018/11/19
 */
public class ForecastDisplay implements Observer, DisplayElement {

    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("This is forecast display!");
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        display();
    }
}
