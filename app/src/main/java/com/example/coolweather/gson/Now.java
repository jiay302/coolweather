package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xuyue on 2018/1/25.
 */

/*
对应JSON格式数据说明
"now": {
			"cond": {
				"code": "407", --实况天气代码
				"txt": "阵雪" --实况天气信息
			},
			"fl": "-9", --体感温度，默认单位：摄氏度
			"hum": "94", --相对湿度
			"pcpn": "0.3", --降水量
			"pres": "1028", --大气压强
			"tmp": "0", --温度，默认单位：摄氏度
			"vis": "10", --能见度，默认单位：公里
			"wind": {
				"deg": "62", --风向360角度
				"dir": "东北风", --风向
				"sc": "3-4", --风力
				"spd": "11" --风速，公里/小时
			}
		}
 */

public class Now {

    @SerializedName("cond")
    public More more;

    public String fl;
    public String hum;
    public String pcpn;
    public String pres;

    @SerializedName("tmp")
    public String temperature;

    public String vis;




    public class More{
        @SerializedName("code")
        public String info_code;

        @SerializedName("txt")
        public String info_txt;
    }

    public class Wind{
        public String deg;
        public String dir;
        public String sc;
        public String spd;
    }
}
