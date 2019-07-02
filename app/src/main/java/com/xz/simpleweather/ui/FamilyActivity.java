package com.xz.simpleweather.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xz.simpleweather.R;
import com.xz.simpleweather.entity.Local;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FamilyActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView webView;
    WebSettings setting ;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
        webView = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        ButterKnife.bind(this);

        webView.loadUrl(Local.MY_WEB);
        setting = webView.getSettings();
        setting.setSupportZoom(true);
//        setting.setBuiltInZoomControls(true);
        setting.setDisplayZoomControls(false);
    }
}
