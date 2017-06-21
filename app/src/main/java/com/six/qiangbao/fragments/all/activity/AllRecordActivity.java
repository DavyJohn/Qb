package com.six.qiangbao.fragments.all.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.saint.netlibrary.APIService;
import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.GoodsQbJuData;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.all.adapter.AllRecordAdapter;
import com.six.qiangbao.utils.AppManager;
import com.six.qiangbao.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 所有抢宝记录
 * Created by 志浩 on 2016/6/20.
 */
public class AllRecordActivity extends BaseActivity {

    @BindView(R.id.all_record_recycler)
    RecyclerView mRecycler;

    @BindView(R.id.all_record_bar)
    Toolbar mToolBar;

    private List<GoodsQbJuData> list = new ArrayList<>();

    private AllRecordAdapter adapter;
    String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_record_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        setupToolbar();
        id = getIntent().getStringExtra("goods_id");
        initData();
    }

    private void initData() {
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerDecoration(this,LinearLayoutManager.VERTICAL));

        adapter = new AllRecordAdapter(this);



    }

    private void setupToolbar() {
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


    private void data(String id){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.qbdata(id)
                .subscribe(newSubscriber(new Action1<List<GoodsQbJuData>>() {
                    @Override
                    public void call(List<GoodsQbJuData> goodsQbJuDatas) {
                        if (goodsQbJuDatas.size() != 0){
                            list.addAll(goodsQbJuDatas);
                            mRecycler.setAdapter(adapter);
                            adapter.addData(goodsQbJuDatas);
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
    @Override
    protected void onStart() {
        super.onStart();
        data(id);
    }
}
