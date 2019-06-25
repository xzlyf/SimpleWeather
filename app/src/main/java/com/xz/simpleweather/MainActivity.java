package com.xz.simpleweather;

import android.os.Bundle;
import android.widget.TextView;

import com.xz.simpleweather.base.BaseActivity;
import com.xz.simpleweather.network.OkhttpClientManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.msg_test)
    TextView msgTest;
    @OnClick(R.id.msg_test)
    public void test(){
        showDialog("警告","测试");
    }
    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void init_Data() {
        ButterKnife.bind(this);


        new OkhttpClientManager().sendRequestWithOkHttp();
    }

}
