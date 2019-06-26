package com.xz.simpleweather.entity;

public class Local {
    public static String REQUEST_LINK = "https://www.mxnzp.com/";//api头地址
    public static String REOUEST_IDENTIFY = "api/";//网址的标识
    public static String REQUEST_GET_WEATHER = "weather/current/";//api尾部
    public static String REQIEST_GET_USER_IP = "ip/self";//api尾部
    public static String BASE_URL = REQUEST_LINK + REOUEST_IDENTIFY;
    public static String WEATHER_URL = BASE_URL+ REQUEST_GET_WEATHER;
    public static String GET_USER_IP_URL = BASE_URL+ REQIEST_GET_USER_IP;

    public static class self{
        public static String ip;//访问者的ip地址
        public static String province;//省份
        public static String city;//城市
        public static String isp;//网络服务商名称
    }
}
