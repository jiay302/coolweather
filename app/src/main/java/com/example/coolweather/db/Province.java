package com.example.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by xuyue on 2018/1/24.
 */

//存放省份数据信息
public class Province extends DataSupport {
    private int id;
    private String provinceName;//省名
    private int provinceCode;//省代号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
