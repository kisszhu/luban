package com.zhl.register.weather;

/**
 * Created by zhuhailong-dc on 2018/5/7.
 * 启动气象站
 * 测试程序
 */
public class WeatherStation {

    public static void main(String[] args){
        WeatherData weatherData=new WeatherData();
        CurrentConditionsDisplay currentDisplay=new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80,65,30.4f);
        weatherData.setMeasurements(82,70,29.2f);
        weatherData.setMeasurements(78,90,30.4f);
    }
}
