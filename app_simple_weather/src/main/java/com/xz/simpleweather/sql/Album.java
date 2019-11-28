package com.xz.simpleweather.sql;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class Album extends DataSupport {
    private Long clock_time;//打卡时间,时间戳
    private int clock_number;//打卡次数
    private boolean isClock;//是否已打卡

    public boolean isClock() {
        return isClock;
    }

    public void setClock(boolean clock) {
        isClock = clock;
    }

    public Long getClock_time() {
        return clock_time;
    }

    public void setClock_time(Long clock_time) {
        this.clock_time = clock_time;
    }

    public int getClock_number() {
        return clock_number;
    }

    public void setClock_number(int clock_number) {
        this.clock_number = clock_number;
    }

}
