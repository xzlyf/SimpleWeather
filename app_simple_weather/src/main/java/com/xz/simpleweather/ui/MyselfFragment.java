package com.xz.simpleweather.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xz.simpleweather.R;
import com.xz.simpleweather.base.BaseFragment;
import com.xz.simpleweather.entity.Local;
import com.xz.simpleweather.entity.UpdateData;
import com.xz.simpleweather.ui.MainActivity.model.IModel;

import org.json.JSONException;
import org.json.JSONObject;

public class MyselfFragment extends BaseFragment implements View.OnClickListener {
    private Button checkUp_btn;
    private Button family_btn;
    private View view;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        }
    };
    private Context context;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_myself;
    }

    @Override
    protected void init_data() {
        findID();
    }

    private void findID() {
        this.view = getView();
        this.context = getContext();
        checkUp_btn = view.findViewById(R.id.check_update_btn);
        family_btn = view.findViewById(R.id.family_btn);
        checkUp_btn.setOnClickListener(this);
        family_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_update_btn:
                showDialog("正在更新，请稍后", "L");
                checkUpdateFromServer();
                break;
            case R.id.family_btn:
                startActivity(new Intent(context,FamilyActivity.class));
                break;
        }
    }

    /**
     * 从服务器检查更新
     */
    private void checkUpdateFromServer() {
        model.getDataFromNet(Local.UPDATE_URL, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                dismissDialog();

                JSONObject obj = null;

                Logger.w("更新数据：" + data);
                try {
                    obj = new JSONObject(data);
                    if (obj.getInt("value") == 1) {
                        JSONObject obj2 = obj.getJSONObject("data");
                        Gson gson = new Gson();
                        UpdateData updateData = gson.fromJson(obj2.toString(), UpdateData.class);
                        showUpdataDialog(updateData);
                    } else {
                        showDialog("检查更新失败", "T");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    showDialog("服务器数据异常", "W");
                }

            }

            @Override
            public void failed(Exception e) {
                dismissDialog();
                showDialog("服务器请求失败", "W");

            }
        });
    }

    /**
     * 更新对话框
     *
     * @param updateData
     */
    private void showUpdataDialog(final UpdateData updateData) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                dismissDialog();
                //判断是否需要更新
                if (Local.localVersionCode>=updateData.getCode()){
                    showDialog("最新版本啦！","T");
                }else {
                    UpdateDialog dialog = new UpdateDialog(context, R.style.base_dialog);
                    dialog.create();
                    dialog.setVersionName("V"+updateData.getName());
                    dialog.setVersionMsg(updateData.getMsg());
                    dialog.setDownloadLink(updateData.getLink());
//                    dialog.setCancelable(false);
//                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }
            }
        });
    }

}
