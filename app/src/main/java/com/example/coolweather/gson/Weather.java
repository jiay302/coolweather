package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xuyue on 2018/1/25.
 */
//天气对应的实体类
public class Weather {
    public AQI aqi;
    public Basic basic;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
    public Now now;
    public String status;
    public Suggestion suggestion;
}
