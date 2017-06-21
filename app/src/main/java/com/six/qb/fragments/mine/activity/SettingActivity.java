package com.six.qb.fragments.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.leaking.slideswitch.SlideSwitch;
import com.six.qb.BaseActivity;
import com.six.qb.R;
import com.six.qb.login.LoginActivity;
import com.six.qb.utils.AppManager;
import com.six.qb.utils.ConstantUtil;
import com.six.qb.utils.DataCleanManager;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class SettingActivity extends BaseActivity {


    @BindView(R.id.slideSwitch)
    SlideSwitch mSlide;
    @BindView(R.id.setting_bar)
    Toolbar mToolbar;

    @OnClick(R.id.exit_btn) void exit(){
        new SharedPrefsCookiePersistor(mContext).clear();
        startActivity(LoginActivity.class);
        finish();
    }


    @OnClick(R.id.clean_lin) void clean(){
        DataCleanManager.cleanCache(this);
        DataCleanManager.cleanSharedPreference(this);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_mine_layout);
        AppManager.getAppManager().addActivity(mContext);
        setupToolBar();
        initData();
    }
    private void setupToolBar(){
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.left_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private  void initData(){
        mSlide.setState(false);
        mSlide.setSlideable(true);
        mSlide.setClickable(true);
        mSlide.setSlideListener(new SlideSwitch.SlideListener() {
            @Override
            public void open() {
                ConstantUtil.isImage = 1;
            }

            @Override
            public void close() {
                ConstantUtil.isImage = 0;
            }
        });


    }
}
