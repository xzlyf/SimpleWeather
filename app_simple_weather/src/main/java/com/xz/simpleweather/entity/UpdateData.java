package com.xz.simpleweather.entity;

public class UpdateData {

    /**
     * level : 0
     * name : 0.2
     * code : 2
     * link : http://xzlyf.club/SimpleWeather/apk/SW.apk
     */

    private int level;
    private String name;
    private int code;
    private String link;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
