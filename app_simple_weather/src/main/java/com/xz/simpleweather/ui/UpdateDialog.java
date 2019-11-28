package com.xz.simpleweather.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.ldoublem.loadingviewlib.LVLineWithText;
import com.ldoublem.loadingviewlib.view.LVBlock;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.orhanobut.logger.Logger;
import com.xz.simpleweather.R;
import com.xz.simpleweather.entity.Local;

import java.io.File;

public class UpdateDialog extends Dialog implements View.OnClickListener {
    private TextView msg;
    private TextView name;
    private Button cancel;
    private Button download;
    private LinearLayout bottom_layout;
    private LVLineWithText download_bar;
    private LVBlock download_gif;
    private TextView tips;
    private Context mContext;
    private View view;
    private String downloadLink;

    public UpdateDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        msg = null;
        name = null;
        cancel = null;
        download = null;
        bottom_layout=null;
        download_bar = null;
        download_gif=null;
        tips = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater1 = LayoutInflater.from(mContext);
        view = inflater1.inflate(R.layout.dialog_update, null);
        setContentView(view);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高
        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕的0.8
        lp.height = (int) (d.heightPixels*0.8);//高度设置为屏幕的0.8
        dialogWindow.setAttributes(lp);

        msg = view.findViewById(R.id.msg_update);
        name = view.findViewById(R.id.name_update);
        cancel = view.findViewById(R.id.cancel_update);
        download = view.findViewById(R.id.download_update);
        bottom_layout = view.findViewById(R.id.layout_bottom_btn);
        download_bar = view.findViewById(R.id.download_progress);
        download_gif = view.findViewById(R.id.download_git);
        tips = view.findViewById(R.id.update_tips);
        cancel.setOnClickListener(this);
        download.setOnClickListener(this);
        download_bar.setViewColor(Color.parseColor("#672F26"));
        download_bar.setTextColor(Color.parseColor("#edb544"));
        download_bar.setVisibility(View.GONE);
        download_gif.setVisibility(View.GONE);
        download_gif.setViewColor(Color.parseColor("#edb544"));

    }

//    @Override
//    public void onBackPressed() {
//        Toast.makeText(mContext,"返回没有用的",Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_update:
                dismiss();
                break;
            case R.id.download_update:
                doDownload();
                break;
        }
    }

    private void doDownload() {
        final String apkName = "/轻巧天气.apk";
        File file = new File(Local.DEFAULT_DOWNLOAD_PATH+apkName);
        if (file.exists())
            file.delete();
        bottom_layout.setVisibility(View.GONE);

        FileDownloader.init(mContext);
        FileDownloader
                .getImpl()
                .create(downloadLink)
                .setWifiRequired(false)
                .setPath(Local.DEFAULT_DOWNLOAD_PATH+apkName)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        download_bar.setVisibility(View.VISIBLE);
                        download_gif.setVisibility(View.VISIBLE);
                        download_gif.startAnim();
                        setCancelable(false);
                        setCanceledOnTouchOutside(false);
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        download_bar.setValue((int) ((float)soFarBytes / totalBytes*100));
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        download_bar.setVisibility(View.INVISIBLE);
                        download_gif.stopAnim();
                        tips.setText("↓点击安装吧↓");
                        download_gif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AppUtils.installApp(Local.DEFAULT_DOWNLOAD_PATH+apkName);
                                dismiss();
                            }
                        });
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        download_bar.setVisibility(View.INVISIBLE);
                        download_gif.stopAnim();
                        download_gif.setVisibility(View.GONE);
                        tips.setText("下载失败请稍后重试吧");
                        tips.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dismiss();
                            }
                        });
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        download_bar.setVisibility(View.INVISIBLE);
                        download_gif.stopAnim();
                        download_gif.setVisibility(View.GONE);
                        tips.setText("下载失败");
                        tips.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dismiss();
                            }
                        });

                    }
                }).start();

    }

    public void setVersionName(String name){
        this.name.setText(name);
    }
    public void setVersionMsg(String msg){
        this.msg.setText(msg);
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }
}
