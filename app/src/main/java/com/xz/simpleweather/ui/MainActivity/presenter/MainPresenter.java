package com.xz.simpleweather.ui.MainActivity.presenter;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xz.simpleweather.entity.Local;
import com.xz.simpleweather.entity.UserNetInfo;
import com.xz.simpleweather.entity.WeatherData;
import com.xz.simpleweather.ui.MainActivity.MainActivity;
import com.xz.simpleweather.ui.MainActivity.model.IModel;
import com.xz.simpleweather.ui.MainActivity.model.MainModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainPresenter {
    private MainModel model;
    private MainActivity view;

    public MainPresenter(MainActivity view) {
        this.view = view;
        model = new MainModel();
    }

    /**
     *
     * @param url
     */
    public void getWeatherDataFromNet(String url) {
        model.getDataFromNet(url, new IModel.OnLoadCompleteListener() {
            @Override
            public void failed(Exception e) {

                view.sToast("网络异常0x0002");
                view.showRetryTips();
            }

            @Override
            public void success(String responseData) {
                JSONObject obj = null;

                try {
                    Logger.w(responseData);
                    obj = new JSONObject(responseData);
                    if (obj.getString("code").equals("1")) {
                        JSONObject obj2 = obj.getJSONObject("data");
                        if (obj2 != null) {
                            Gson gson = new Gson();
                            WeatherData weatherData = gson.fromJson(obj2.toString(), WeatherData.class);
                            Local.isloading = true;//加载完成标识
                            view.setDataToView(weatherData);
                        }


                    } else if (obj.getString("code").equals("0")) {
                        view.sToast("解析失败：" + obj.getString("msg"));
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                    view.sToast("请求失败");
                }
            }
        });
    }

    /**
     * 获取用户网络位置 IP  服务商信息
     */
    public void getUserIpFromNet() {
        model.getDataFromNet(Local.GET_USER_IP_URL, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;

                try {
                    Logger.w(data);
                    obj = new JSONObject(data);
                    if (obj.getString("code").equals("1")) {
                        JSONObject obj2 = obj.getJSONObject("data");
                        if (obj2 != null) {
                            Gson gson = new Gson();
                            UserNetInfo info = gson.fromJson(obj2.toString(),UserNetInfo.class);
                            //赋值给全局变量
                            Local.self.city = info.getCity();
                            Local.self.ip = info.getIp();
                            Local.self.isp = info.getIsp();
                            Local.self.province = info.getProvince();

                            //随机两秒内
                            Random random = new Random();
                            int time = random.nextInt(2000);
                            Thread.sleep(time);//延迟一下才给结果，让加载动画播放下，模拟网络慢
                            view.getWeatherData();
                            view.dismissDialog();
                        }


                    } else if (obj.getString("code").equals("0")) {
                        view.sToast("获取当前位置失败：" + obj.getString("msg"));
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                    view.sToast("获取当前位置失败");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    view.sToast("程序异常");
                }
            }

            @Override
            public void failed(Exception e) {
                try {
                    Thread.sleep(1000);//模拟网络慢
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                view.sToast("网络异常0x0001");
                view.dismissDialog();
                view.showRetryTips();

            }
        });
    }


}
