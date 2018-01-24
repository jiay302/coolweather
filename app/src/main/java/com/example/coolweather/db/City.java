package com.example.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by xuyue on 2018/1/24.
 */

//存放市区数据
public class City extends DataSupport {

    private int id;
    private String cityName;//市名
    private int cityCode;//市代号
    private int provinceId;//市所属的省份的id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
