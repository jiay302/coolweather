package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xuyue on 2018/1/25.
 */

/*对应的JSON数据说明

"basic": {
			"city": "苏州", --城市名
			"cnty": "中国", --所属国家名
			"id": "CN101190401", --weather_id
			"lat": "31.29937935", --经度
			"lon": "120.61958313", --纬度
			"update": { --天气更新时间，24小时制
				"loc": "2018-01-25 10:15", --当地时间
				"utc": "2018-01-25 02:15" --UTC时间
			}
		}

 */

public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("cnty")
    public String nationName;

    @SerializedName("id")
    public String weatherId;

    @SerializedName("lat")
    public String cityLat;

    @SerializedName("lon")
    public String cityLon;

    public Update update;

    public class Update{

        @SerializedName("loc")
        public String updateLocTime;

        @SerializedName("utc")
        public String updateUtcTime;
    }

}
