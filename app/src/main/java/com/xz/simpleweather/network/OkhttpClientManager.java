package com.xz.simpleweather.network;

import android.util.Log;

import com.orhanobut.logger.Logger;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpClientManager {
    public void sendRequestWithOkHttp() {


        new Thread(new Runnable() {

            @Override

            public void run() {


                try {


                    //get请求

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.mxnzp.com/api/address/list")
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

                    Logger.d(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }).start();

    }


}
