package com.xz.simpleweather.network;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpClientManager {

    public static void sendRequestWithOkHttp(final String url, final ResultCallback callback) {

        new Thread(new Runnable() {

            @Override

            public void run() {
                try {
                    //get请求
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();

                    //post请求
                    /*
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", "20100539")
                            .add("password", "123456")
                            .build();
                    Request request = new Request.Builder()
                            .url("http://49.122.0.187/Hall/api/loginVerify")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    */
                    callback.onResponse(responseData);
                } catch (Exception e) {
                    callback.onError(e);
                }

            }

        }).start();

    }

    public static abstract class ResultCallback<T> {

        public abstract void onError(Exception e);

        public abstract void onResponse(String responseData);
    }

}
