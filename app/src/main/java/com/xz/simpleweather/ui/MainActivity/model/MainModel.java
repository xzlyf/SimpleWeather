package com.xz.simpleweather.ui.MainActivity.model;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainModel implements IModel {
    @Override
    public void getDataFromNet(final String url, final OnLoadCompleteListener onLoadCompleteListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = null;
                    response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    onLoadCompleteListener.success(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                    onLoadCompleteListener.failed(e);
                }
            }
        }).start();
    }
}
