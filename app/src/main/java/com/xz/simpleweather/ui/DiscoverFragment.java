package com.xz.simpleweather.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xz.simpleweather.R;
import com.xz.simpleweather.base.BaseFragment;

import java.util.logging.Logger;

public class DiscoverFragment extends BaseFragment {
    private View view;
    private ImageView money_gif;
    private Button clock_in_btn;
    private Context context;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_discover;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    protected void init_data() {
        findID();
        init_listeners();
    }


    private void init_listeners() {
        clock_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
            }
        });
    }

    private void anim() {
        Glide.with(context).load(R.drawable.money).into(money_gif);
    }

    private void findID() {
        this.context = getContext();
        this.view = getView();
        money_gif = view.findViewById(R.id.money_gif);
        clock_in_btn = view.findViewById(R.id.clock_in_btn);
    }

}
