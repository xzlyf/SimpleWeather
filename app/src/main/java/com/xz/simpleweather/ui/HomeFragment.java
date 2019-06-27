package com.xz.simpleweather.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ldoublem.loadingviewlib.view.LVBlazeWood;
import com.orhanobut.logger.Logger;
import com.xz.simpleweather.R;
import com.xz.simpleweather.base.BaseFragment;
import com.xz.simpleweather.entity.Local;
import com.xz.simpleweather.entity.WeatherForecastData;
import com.xz.simpleweather.ui.MainActivity.model.IModel;
import com.xz.simpleweather.ui.MainActivity.model.MainModel;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends BaseFragment {
    private View view;
    private Context context;
    private RecyclerView forecastRecycler;
    private LVBlazeWood lvBlazeWood;
    private RelativeLayout relativeLayout;
    private MainModel model;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init_data() {
        view = getView();
        context = getContext();
        model = new MainModel();
        //接受就绪广播
        findID();
        startAnim();
    }

    private void requestForNet() {
        model.getDataFromNet(Local.FORECAST_WEATHER_URL+Local.self.city, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;

                try {
                    Logger.w("未来天气"+data);
                    obj = new JSONObject(data);
                    if (obj.getString("code").equals("1")) {
                            Gson gson = new Gson();
                            WeatherForecastData weatherForecastData = gson.fromJson(obj.toString(), WeatherForecastData.class);


                    } else if (obj.getString("code").equals("0")) {
//                        view.sToast("解析失败：" + obj.getString("msg"));
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
//                    view.sToast("请求失败");
                }
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }

    /**
     * findViewById
     */
    private void findID() {
        forecastRecycler = view.findViewById(R.id.forecast_recycler);
        lvBlazeWood = view.findViewById(R.id.home_loading);
        relativeLayout = view.findViewById(R.id.home_loading_layout);
    }

    private void startAnim(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                lvBlazeWood.startAnim();
            }
        });
    }
    private void stopAnim(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                lvBlazeWood.stopAnim();
                relativeLayout.setVisibility(View.GONE);
            }
        });
    }

}
