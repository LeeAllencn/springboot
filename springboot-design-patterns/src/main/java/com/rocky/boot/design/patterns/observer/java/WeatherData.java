package com.rocky.boot.design.patterns.observer.java;

import java.util.Observable;

/**
 * @author rocky
 * description: 使用java内置的观察者模式
 * @date Created in 2018/11/19
 */
public class WeatherData extends Observable {

    private float temperature;

    private float humidity;

    private float pressure;

    public WeatherData() {
    }

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
