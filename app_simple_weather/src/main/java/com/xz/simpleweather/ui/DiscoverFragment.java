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
import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Random;

public class DiscoverFragment extends BaseFragment {


    private View view;
    private TextView tips;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void init_data() {
        this.view = getView();
        tips = view.findViewById(R.id.tips);
        tips.setText(TimeCycle.getWeek(Local.netTime));
    }

}
