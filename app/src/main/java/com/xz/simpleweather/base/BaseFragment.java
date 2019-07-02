package com.xz.simpleweather.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xz.simpleweather.R;
import com.xz.simpleweather.ui.MainActivity.model.MainModel;

public abstract class BaseFragment extends Fragment {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private Context mContext;
    private BaseDialog mDialog;
    private View view;
    public MainModel model;

    protected abstract int getLayoutResource();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        model = new MainModel();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract void init_data();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutResource(), container, false);
        init_data();

        return view;
    }

    //常用方法
    public View getView() {
        return view;
    }

    public Context getContext() {
        return mContext;
    }

    Toast mToast;

    public void mToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }


    public void showDialog(final String msg, final String type) {
        handler.post(new Runnable() {
            @Override
            public void run() {
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
        });

    }

    public void dismissDialog() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                    mDialog = null;
                }
            }
        });
    }

}
