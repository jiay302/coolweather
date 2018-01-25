package com.example.coolweather.gson;


import com.google.gson.annotations.SerializedName;

/**
 * Created by xuyue on 2018/1/25.
 */

/*
一天天气预报对应的JSON格式数据说明
daily_forecast": {
			"astro": {
				"mr": "11:47", --月升时间
				"ms": "00:02", --月落时间
				"sr": "06:54", --日出时间
				"ss": "17:27" --日落时间
			},
			"cond": {
				"code_d": "403", --白天天气状况代码
				"code_n": "402", --晚间天气状况代码
				"txt_d": "暴雪", --白天天气状况描述
				"txt_n": "大雪" --晚间天气状况描述
			},
			"date": "2018-01-25", --预报日期
			"hum": "86", --相对湿度
			"pcpn": "19.0", --降水量
			"pop": "98", --降水概率
			"pres": "1029", --大气压强
			"tmp": {
				"max": "2", --最高温度
				"min": "-1" --最低温度
			},
			"uv": "2", --紫外线强度指数
			"vis": "13", --能见度，单位：公里
			"wind": {
				"deg": "48", --风向360角度
				"dir": "东北风", --风向
				"sc": "4-5", --风力
				"spd": "20" --风速，公里/小时
			}
		}
 */

public class Forecast {

    public Astro astro;

    @SerializedName("cond")
    public More more;

    public String date;
    public String hum;
    public String pcpn;
    public String pop;
    public String pres;

    @SerializedName("tmp")
    public Temperature temperature;

    public String uv;
    public String vis;
    public Wind wind;

    public class Astro{
        public String mr;
        public String ms;
        public String sr;
        public String ss;
    }

    public class More{
        @SerializedName("code_d")
        public String info_day_code;
        @SerializedName("code_n")
        public String info_night_code;

        @SerializedName("txt_d")
        public String info_day_txt;
        @SerializedName("txt_n")
        public String info_night_txt;
    }

    public class Temperature{
        public String max;
        public String min;
    }

    public class Wind{
        public String deg;
        public String dir;
        public String sc;
        public String spd;
    }
}
