package com.xz.simpleweather.ui.MainActivity.model;

public interface IModel {
    //从网络获取数据
    void getDataFromNet(String url,OnLoadCompleteListener onLoadCompleteListener);

    //回调接口
    interface OnLoadCompleteListener{
        void success(String data);
        void failed(Exception e);
    }
}
