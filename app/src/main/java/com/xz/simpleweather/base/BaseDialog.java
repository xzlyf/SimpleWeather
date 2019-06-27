package com.xz.simpleweather.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xz.simpleweather.R;

public class BaseDialog extends Dialog {
    private TextView msg;
    private TextView title;
    private Context mContext;
    private ProgressBar progressBar;
    private static final String TYPE_WORNNING = "W";
    private static final String TYPE_ERROR = "E";
    private static final String TYPE_TIPS = "T";
    private static final String TYPE_LOAD = "L";

    public BaseDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }



    @Override
    public void setTitle(@Nullable CharSequence title) {
        this.title.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }

    public void setMes(String msg) {
        this.msg.setText(msg);
    }
    public void setType(String type){
        if (type.equals(TYPE_TIPS)){
            //提示
            title.setText("提示");
        }else if (type.equals(TYPE_ERROR)){
            //错误
            title.setText("错误");

        }else if (type.equals(TYPE_WORNNING)){
            //警告
            title.setText("警告");
        }else if (type.equals(TYPE_LOAD)){
            //加载
            title.setText("加载中");
            progressBar.setVisibility(View.VISIBLE);
            setCancelable(false);
            setCanceledOnTouchOutside(false);
        }else{
            title.setText("消息");
        }
    }
    @Override
    public void create() {
        super.create();

        LayoutInflater inflater1 = LayoutInflater.from(mContext);
        View view1 = inflater1.inflate(R.layout.dialog_base, null);
        setContentView(view1);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);

        msg = findViewById(R.id.base_dialog_msg);
        title = findViewById(R.id.base_dialog_title);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        msg = null;
        title = null;
    }

}
