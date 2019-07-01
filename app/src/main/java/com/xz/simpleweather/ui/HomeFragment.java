package com.xz.simpleweather.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.xz.simpleweather.ui.adapter.ForecastWeatherAdapter;
import com.xz.simpleweather.utils.SpaceItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragment extends BaseFragment {
    private View view;
    private Context context;
    private RecyclerView forecastRecycler;
    private LVBlazeWood lvBlazeWood;
    private RelativeLayout relativeLayout;
    private MainModel model;
    private RecyclerView mRecycler;
    private ForecastWeatherAdapter adapter;

    Handler handler = new Handler() {
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
        findID();
        startAnim();
        requestForNet();
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 处理请求回来的数据
     */
    private void requestForNet() {
        model.getDataFromNet(Local.FORECAST_WEATHER_URL + Local.self.city, new IModel.OnLoadCompleteListener() {

            @Override
            public void success(String data) {
                JSONObject obj = null;

                try {
                    Logger.w("未来天气" + data);
                    obj = new JSONObject(data);
                    if (obj.getString("code").equals("1")) {
                        Gson gson = new Gson();
                        WeatherForecastData weatherForecastData = gson.fromJson(obj.toString(), WeatherForecastData.class);
                        //随机两秒内
                        Random random = new Random();
                        int time = random.nextInt(2000);
                        Thread.sleep(time);//延迟一下才给结果，让加载动画播放下，模拟网络慢
                        setWeatherForecastData(weatherForecastData);

                    } else if (obj.getString("code").equals("0")) {
//                        view.sToast("解析失败：" + obj.getString("msg"));
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
//                    view.sToast("请求失败");
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

    private void startAnim() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                relativeLayout.setVisibility(View.VISIBLE);
                lvBlazeWood.startAnim();
            }
        });
    }

    private void stopAnim() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                lvBlazeWood.stopAnim();
                relativeLayout.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 显示数据
     *
     * @param weatherForecastData
     */
    public void setWeatherForecastData(final WeatherForecastData weatherForecastData) {
        if (weatherForecastData == null) return;
        handler.post(new Runnable() {
            @Override
            public void run() {
                mRecycler = view.findViewById(R.id.forecast_recycler);
                adapter = new ForecastWeatherAdapter(weatherForecastData,context);
                mRecycler.setLayoutManager(new LinearLayoutManager(context));
                mRecycler.addItemDecoration(new SpaceItemDecoration(60));//item的间距
                mRecycler.setAdapter(adapter);
                stopAnim();
            }
        });

    }

}
