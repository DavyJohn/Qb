package com.six.qiangbao.fragments.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.MyQbJu;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class ShopActivity extends BaseActivity {

    @BindView(R.id.shop_bar)
    Toolbar mToolBar;
    @BindView(R.id.shop_recycler)
    RecyclerView mRecycler;
    private String start;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_main_layout);
        setUpToolBar();
        start = String.valueOf(0);
        data();
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

    /**
     * 已经获得的商品
     * */
    private void data(){
        String num = start;
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.owned(num,String.valueOf(10))
                .subscribe(newSubscriber(new Action1<MyQbJu>() {
                    @Override
                    public void call(MyQbJu myQbJu) {
                        System.out.print(myQbJu);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
