package com.xz.simpleweather.utils;

import com.xz.simpleweather.sql.Album;

import org.litepal.crud.DataSupport;

import java.util.List;

public class OperateSql {
    /**
     * 向数据库保存打卡信息
     * @param number 打卡次数
     * @param time 打卡时间
     * @return true 保存成功  反之
     */
    public static boolean saveToday(int number, long time){
        Album album = new Album();
        album.setClock_number(number);
        album.setClock_time(time);
        album.setClock(true);
        return album.save();
    }

    /**
     * 查询表所有字段
     * @return 每个集合代表一天
     */
    public static List<Album> queryAll(){
        List<Album> album = DataSupport.findAll(Album.class);
        return album;
    }
}
