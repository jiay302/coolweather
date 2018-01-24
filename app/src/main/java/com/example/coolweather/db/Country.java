package com.example.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by xuyue on 2018/1/24.
 */

//存放县数据
public class Country extends DataSupport {
    private int id;
    private String countryName;//县名
    private String weatherId;//对应天气id
    private int cityId;//所属城市的id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
