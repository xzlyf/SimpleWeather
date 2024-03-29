package com.xz.simpleweather.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xz.simpleweather.R;

/**
 * 基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Activity mContext;
    private BaseDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //公共属性
        mContext = this;
        Logger.addLogAdapter(new AndroidLogAdapter());
        //公共方法
        setContentView(getLayoutResource());
        init_Data();
    }


    Toast mToast;

    public abstract int getLayoutResource();

    public abstract void init_Data();

    //常用方法
    public void mToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showDialog(String msg,String type) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
        mDialog = new BaseDialog(mContext, R.style.base_dialog);//创建Dialog并设置样式主题
        mDialog.create();
        mDialog.setMes(msg);
        mDialog.setType(type);
        mDialog.setCancelable(true);
        mDialog.show();

    }

    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    /**
     * 判断是否在当前主线程 *
     * @return
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

}
