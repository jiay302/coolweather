package com.example.coolweather.gson;

/**
 * Created by xuyue on 2018/1/25.
 */

/*
对应JSON格式数据说明
"aqi": {
			"city": {
				"aqi": "36", --空气质量指数
				"qlty": "优", --空气质量，取值范围:优，良，轻度污染，中度污染，重度污染，严重污染
				"pm25": "22", --pm25
				"pm10": "36", --pm10
				"no2": "44", --二氧化氮
				"so2": "7", --二氧化硫
				"co": "1", --一氧化碳
				"o3": "38" --臭氧
			}
		}

 */
public class AQI {

    public AQICity city;

    public class AQICity{
        public String aqi;
        public String qlty;
        public String pm25;
        public String pm10;
        public String no2;
        public String so2;
        public String co;
        public String o3;
    }
}
