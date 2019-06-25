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
import android.widget.TextView;

import com.xz.simpleweather.R;

public class BaseDialog extends Dialog {
    private TextView msg;
    private TextView title;
    private Context mContext;

    public BaseDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void setTitle(@Nullable CharSequence title) {
        this.title.setText(title);
    }

    public void setMes(String msg) {
        this.msg.setText(msg);
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
    }

    @Override
    public void dismiss() {
        super.dismiss();
        msg = null;
        title = null;
    }

}
