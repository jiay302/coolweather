package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xuyue on 2018/1/25.
 */

/*
对应JSON格式数据说明
brf	生活指数简介
txt	生活指数详细描述
comf：舒适度指数、cw：洗车指数、drsg：穿衣指数、flu：感冒指数、sport：运动指数、trav：旅游指数、uv：紫外线指数、air：空气污染扩散条件指数
 */
public class Suggestion {

    public Air air; //空气污染扩散条件指数
    @SerializedName("comf")
    public Comfort comfort; //舒适度指数
    @SerializedName("cw")
    public CarWash carWash; //洗车指数
    public Dress drsg; //穿衣指数
    public Flu flu; //感冒指数
    public Sport sport; //运动指数
    public Travel trav; //旅游指数
    public UV uv; //紫外线指数


    public class Air{
        @SerializedName("brf")
        public String simpleInfo;

        @SerializedName("txt")
        public String detailInfo;
    }

    public class Comfort{
        @SerializedName("brf")
        public String simpleInfo;

        @SerializedName("txt")
        public String detailInfo;
    }

    public class CarWash{
        @SerializedName("brf")
        public String simpleInfo;

        @SerializedName("txt")
        public String detailInfo;
    }

    public class Dress{
        @SerializedName("brf")
        public String simpleInfo;

        @SerializedName("txt")
        public String detailInfo;
    }

    public class Flu{
        @SerializedName("brf")
        public String simpleInfo;

        @SerializedName("txt")
        public String detailInfo;
    }

    public class Sport{
        @SerializedName("brf")
        public String simpleInfo;

        @SerializedName("txt")
        public String detailInfo;
    }

    public class Travel{
        @SerializedName("brf")
        public String simpleInfo;

        @SerializedName("txt")
        public String detailInfo;
    }

    public class UV{
        @SerializedName("brf")
        public String simpleInfo;

        @SerializedName("txt")
        public String detailInfo;
    }
}
