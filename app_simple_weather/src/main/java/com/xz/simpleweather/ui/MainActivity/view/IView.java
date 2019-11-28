package com.xz.simpleweather.ui.MainActivity.view;

import com.xz.simpleweather.entity.WeatherData;

public interface IView {
    void sToast(String msg);
    void cleanAll();
    void showRetryTips();
    void startLoginning();
    void stopLoginning();
}
