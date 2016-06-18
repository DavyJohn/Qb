package com.six.qiangbao.fragments.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;

import butterknife.Bind;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class ShopActivity extends BaseActivity {

    @Bind(R.id.shop_bar)
    Toolbar mToolBar;
    @Bind(R.id.shop_recycler)
    RecyclerView mRecycler;
    @Bind(R.id.shop_linear)
    LinearLayout mLin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_main_layout);
        setUpToolBar();
    }

    private void setUpToolBar() {
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.left_icon);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
