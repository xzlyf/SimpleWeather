package com.xz.simpleweather.ui.MainActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.ldoublem.loadingviewlib.view.LVEatBeans;
import com.orhanobut.logger.Logger;
import com.xz.simpleweather.R;
import com.xz.simpleweather.base.BaseActivity;
import com.xz.simpleweather.entity.Local;
import com.xz.simpleweather.entity.WeatherData;
import com.xz.simpleweather.sql.Album;
import com.xz.simpleweather.ui.DiscoverFragment;
import com.xz.simpleweather.ui.HomeFragment;
import com.xz.simpleweather.ui.MainActivity.presenter.MainPresenter;
import com.xz.simpleweather.ui.MainActivity.view.IView;
import com.xz.simpleweather.ui.MyselfFragment;
import com.xz.simpleweather.ui.adapter.TitleFragmentPagerAdapter;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IView {


    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.temp)
    TextView temp;
    @BindView(R.id.weather)
    TextView weather;
    @BindView(R.id.reportTime)
    TextView reportTime;
    @BindView(R.id.windDirection)
    TextView windDirection;
    @BindView(R.id.windPower)
    TextView windPower;
    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.login_animation)
    LVEatBeans loginAnimation;
    @BindView(R.id.login_view)
    RelativeLayout loginView;
    @BindView(R.id.weather_info)
    RelativeLayout weatherInfo;
    @BindView(R.id.loading_tips)
    TextView loading_tips;

    private MainPresenter presenter;
    private Album last_album;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void init_Data() {

        ButterKnife.bind(this);
        presenter = new MainPresenter(this);//初始化presenter
        startLoginning();//显示动画
        presenter.getUserIpFromNet();
        LitePal.initialize(this);//初始化litepal
        LitePal.getDatabase();//如果没有表则创建一张表
        Album last_album = DataSupport.findLast(Album.class);//查找表的最后一条数据
        if (last_album!=null){
            Local.self.last_clock_number=last_album.getClock_number();
            Local.self.isClockToday = last_album.isClock();
            Local.self.clock_time = last_album.getClock_time();
        }

        //获取本地一些数据
        Local.localVersionName = AppUtils.getAppVersionName();
        Local.localVersionCode = AppUtils.getAppVersionCode();
        Local.DEFAULT_DOWNLOAD_PATH = getExternalCacheDir().getAbsolutePath()+"/apk";
    }


    private void initTab() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout three_tablayout = findViewById(R.id.tab_layout);

        List<Fragment> fragments = new ArrayList<>();
        //加入布局
        fragments.add(new DiscoverFragment());
        fragments.add(new HomeFragment());
        fragments.add(new MyselfFragment());

        String[] titles = new String[]{"发现", "主页", "我的"};

        //设置适配器
        TitleFragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);//设定默认页
        //绑定
        three_tablayout.setupWithViewPager(viewPager);

        for (int i = 0; i < three_tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = three_tablayout.getTabAt(i);
            Drawable d = null;
            switch (i) {
                case 0:
                    d = getResources().getDrawable(R.drawable.home);
                    break;
                case 1:
                    d = getResources().getDrawable(R.drawable.discovery);
                    break;
                case 2:
                    d = getResources().getDrawable(R.drawable.myself);
                    break;
            }
            tab.setIcon(d);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void sToast(final String msg) {

        if (isOnMainThread()) {
            mToast(msg);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mToast(msg);
                }
            });
        }
    }

    @Override
    public void cleanAll() {

    }


    public void setDataToView(final WeatherData weatherData) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                address.setText(weatherData.getAddress());
                temp.setText(weatherData.getTemp());
                weather.setText(weatherData.getWeather());
                windDirection.setText(weatherData.getWindDirection());
                windPower.setText(weatherData.getWindPower());
                humidity.setText(weatherData.getHumidity());
                reportTime.setText(formatTime(weatherData.getReportTime()));
                initTab();
                stopLoginning();
            }
        });

    }

    @Override
    public void showRetryTips() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                loading_tips.setText("点击重试");
                loading_tips.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog("稍等片刻", "L");
                        presenter.getUserIpFromNet();//重新获取一遍地址
                        loading_tips.setText("正在重试");

                    }
                });
            }
        });
    }

    @Override
    public void startLoginning() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                loginAnimation.startAnim(3000);
                loginView.setVisibility(View.VISIBLE);
                weatherInfo.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void stopLoginning() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                loginAnimation.stopAnim();
                loginView.setVisibility(View.GONE);
                weatherInfo.setVisibility(View.VISIBLE);

            }
        });

    }
    public void getWeatherData(){
        //执行查询天气
        presenter.getWeatherDataFromNet(
                Local.WEATHER_URL + Local.self.city);
    }
    /**
     * 格式化日期
     *
     * @param reportTime
     * @return
     */
    private String formatTime(String reportTime) {
        //2019-06-26 13:15:54
        try {
            //因为接收回来的日期是字符串格式要转
            SimpleDateFormat dateP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateP.parse(reportTime);
            SimpleDateFormat dateF = new SimpleDateFormat("MM/dd");
            return dateF.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "null";
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
