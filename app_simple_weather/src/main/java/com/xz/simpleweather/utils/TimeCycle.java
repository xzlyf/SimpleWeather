package com.xz.simpleweather.utils;

import com.xz.simpleweather.entity.Local;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class TimeCycle {
    /**
     * 时间戳转时间
     * 制定格式
     *
     * @param timestamp
     * @return
     */
    public static String string2date(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp);
        return simpleDateFormat.format(date);

    }

    /**
     * 获取星期
     *
     * @param timestamp
     * @return
     */
    public static String getWeek(long timestamp) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(timestamp));

        int week = cd.get(Calendar.DAY_OF_WEEK);

        switch (week) {
            case Calendar.SUNDAY:
                return "周日";
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "什么？今天是周五";
            case Calendar.SATURDAY:
                return "周六快活";
        }

        return "Null";

    }


}
