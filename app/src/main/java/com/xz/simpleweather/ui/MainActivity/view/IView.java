package com.xz.simpleweather.ui.MainActivity.view;

import com.xz.simpleweather.entity.WeatherData;

public interface IView {
    void sToast(String msg);
    void cleanAll();
    void setDataToView(WeatherData weatherData);
    void startLoginning();
    void stopLoginning();
}
