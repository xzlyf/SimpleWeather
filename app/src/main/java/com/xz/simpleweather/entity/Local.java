package com.xz.simpleweather.entity;

public class Local {
    public static String REQUEST_LINK = "https://www.mxnzp.com/";//api头地址
    public static String REQUEST_IDENTIFY = "api/";//网址的标识
    public static String REQUEST_GET_WEATHER = "weather/current/";//api尾部
    public static String REQUEST_GET_USER_IP = "ip/self";//api尾部
    public static String BASE_URL = REQUEST_LINK + REQUEST_IDENTIFY;
    public static String WEATHER_URL = BASE_URL+ REQUEST_GET_WEATHER;
    public static String GET_USER_IP_URL = BASE_URL+ REQUEST_GET_USER_IP;

    public static String REQUEST_GET_FORECAST_WEATHER = "weather/forecast/";//api尾部
    public static String FORECAST_WEATHER_URL = BASE_URL+REQUEST_GET_FORECAST_WEATHER;
    public static String GET_NET_TIME_URL ="http://api.m.taobao.com/rest/api3.do?api=mtop.common.getTimestamp";//网络时间戳api

    public static boolean isloading = false;//是否加载完成
    public static final String clockToday = "ClockToday";
    public static long netTime ;//网络时间戳
    public static final String ALL_READY = "com.xz.simpleweather.ALL_READY";

    public static class self{
        public static String ip;//访问者的ip地址
        public static String province;//省份
        public static String city;//城市
        public static String isp;//网络服务商名称

        public static int last_clock_number= 0;//打卡次数
        public static boolean isClockToday = false;//是否已打卡
        public static long clock_time = 0;//打卡时间

    }
}
