package com.xz.simpleweather.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.xz.simpleweather.R;
import com.xz.simpleweather.base.BaseFragment;
import com.xz.simpleweather.entity.Local;
import com.xz.simpleweather.sql.Album;
import com.xz.simpleweather.ui.MainActivity.model.IModel;
import com.xz.simpleweather.ui.MainActivity.model.MainModel;
import com.xz.simpleweather.utils.OperateSql;
import com.xz.simpleweather.utils.TimeCycle;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;
import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;
import org.litepal.util.Const;

import java.sql.Time;
import java.util.Random;

public class DiscoverFragment extends BaseFragment {
    private View view;
    //    private ImageView money_gif;
    private Button click_in_btn;
    private TextView clock_date;
    private TextView clock_tips;
    private Context context;
    private MainModel model;
    private final static int HIDE_UI = 100;
    private final static int HIDE_CLOCK = 101;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HIDE_UI:
//                    money_gif.setImageDrawable(null);
//                    money_gif.setVisibility(View.GONE);
                    break;
                case HIDE_CLOCK:
                    dismissDialog();
                    clock_date.setText("已打卡" + (Local.self.last_clock_number + 1) + "天");
                    clock_date.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_discover;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void init_data() {
        findID();
        init_listeners();
        model = new MainModel();
        //判断是否第二天要重新打开
        String lcoaltime = TimeCycle.string2date(1561859548);
        String cloudtime = TimeCycle.string2date(System.currentTimeMillis());
        Logger.d(lcoaltime+TimeCycle.IsYesterday(lcoaltime));
        if (!TimeCycle.IsYesterday(lcoaltime)) {
            clock_date.setText("已打卡" + (Local.self.last_clock_number ) + "天");
            clock_date.setVisibility(View.VISIBLE);
        } else {
            click_in_btn.setVisibility(View.VISIBLE);
        }
    }


    private void init_listeners() {
        click_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_in_btn.setEnabled(false);
//                Glide.with(context).load(R.drawable.money).into(money_gif);
                //n秒后隐藏掉money控件
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        money_gif.setVisibility(View.GONE);
//                        click_in_btn.setVisibility(View.GONE);
//                        money_gif = null;
//                        click_in_btn = null;
//                    }
//                },1000);
                showDialog("正在打卡，请等待...", "L");
                getNetTime();
                click_in_btn.setVisibility(View.GONE);
                click_in_btn = null;

            }
        });
    }

    private void findID() {
        this.context = getContext();
        this.view = getView();
//        money_gif = view.findViewById(R.id.money_gif);
        click_in_btn = view.findViewById(R.id.clock_in_btn);
        clock_date = view.findViewById(R.id.clock_date_num);
        clock_tips = view.findViewById(R.id.clock_tips);

    }

    /**
     * 获取时间戳
     */
    public void getNetTime() {
        model.getDataFromNet(Local.GET_NET_TIME_URL, new IModel.OnLoadCompleteListener() {
            @Override
            public void success(String data) {
                JSONObject obj = null;
                try {
                    Logger.w("网络时间戳" + data);
                    obj = new JSONObject(data);
                    if (obj.getJSONObject("data") != null) {
                        JSONObject obj2 = obj.getJSONObject("data");
                        //保存网络时间戳
                        Local.netTime = obj2.getLong("t");
                        //保存数据库
                        OperateSql.saveToday(Local.self.last_clock_number + 1, obj2.getLong("t"));
                        //随机两秒内
                        Random random = new Random();
                        int time = random.nextInt(2000);
                        Thread.sleep(time);//延迟一下才给结果，让加载动画播放下，模拟网络慢
                        Message msg = new Message();
                        msg.what = HIDE_CLOCK;
                        handler.sendMessage(msg);
                    } else {
                        //保存本地时间戳
                        saveLocaltime();
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                    //保存本地时间戳
                    saveLocaltime();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failed(Exception e) {
                //保存本地时间戳
                saveLocaltime();
            }
        });

    }

    private void saveLocaltime() {
        OperateSql.saveToday(Local.self.last_clock_number + 1, System.currentTimeMillis());
        Message msg = new Message();
        msg.what = HIDE_CLOCK;
        handler.sendMessage(msg);
    }
}
