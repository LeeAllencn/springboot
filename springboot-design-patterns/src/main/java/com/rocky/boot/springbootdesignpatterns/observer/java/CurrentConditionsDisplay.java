package com.rocky.boot.springbootdesignpatterns.observer.java;

import com.rocky.boot.springbootdesignpatterns.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * @author rocky
 * @Description: 使用java内置的观察者模式
 * @Date: Created in 2018/11/19
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private Observable observable;

    private float temperature;

    private float humidity;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions:" + temperature + "F degrees and " + humidity + "% humidity");
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}
