package com.xz.simpleweather.ui.MainActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ldoublem.loadingviewlib.view.LVEatBeans;
import com.xz.simpleweather.R;
import com.xz.simpleweather.base.BaseActivity;
import com.xz.simpleweather.entity.Local;
import com.xz.simpleweather.entity.WeatherData;
import com.xz.simpleweather.ui.MainActivity.presenter.MainPresenter;
import com.xz.simpleweather.ui.MainActivity.view.IView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

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

    private MainPresenter presenter;
    private boolean isLoginning;//是否显示着加载动画


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
        presenter.getUserIpFromNet();//获取用户的ip地址可得知用户的网络位置

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


    @Override
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
            }
        });

    }

    @Override
    public void startLoginning() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                isLoginning = true;
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
                isLoginning = false;
                loginAnimation.stopAnim();
                loginView.setVisibility(View.GONE);
                weatherInfo.setVisibility(View.VISIBLE);
                //执行查询天气
                presenter.getWeatherDataFromNet(
                        Local.WEATHER_URL + Local.self.city);
            }
        });

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