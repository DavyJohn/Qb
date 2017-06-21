package com.six.qb.fragments.all.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.ShopDetails;
import com.saint.netlibrary.model.ShopDetailsQData;

import com.six.qb.BaseActivity;
import com.six.qb.R;
import com.six.qb.activitys.JxResultactivity;
import com.six.qb.fragments.all.adapter.QbHistoryAdapter;
import com.six.qb.utils.AppManager;
import com.six.qb.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/22.
 */
public class QbHistoryActivity extends BaseActivity {

    @BindView(R.id.qiangbao_history_bar)
    Toolbar mToolbar;
    @BindView(R.id.history_recycler)
    RecyclerView mRecycler;
    private String id;
    private List<ShopDetailsQData> list = new ArrayList<>();
    private QbHistoryAdapter adapter;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qiangbao_history_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        id = getIntent().getStringExtra("id");
        setUpToolabr();
        initdata();
    }

    private void initdata() {
        data();
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new QbHistoryAdapter(getApplicationContext());
        adapter.setOnItemClickListener(new QbHistoryAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion) {
                if (postion == 0) {
                    finish();
                } else {
                    url = com.saint.netlibrary.utils.ConstantUtil.API_HOST+list.get(postion).url;
                    Intent intent = new Intent(QbHistoryActivity.this, JxResultactivity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    private void setUpToolabr() {
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

    private void data() {
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.shopDetails(String.valueOf(id))
                .subscribe(newSubscriber(new Action1<ShopDetails>() {
                    @Override
                    public void call(ShopDetails wqgoods) {
                        list.clear();
                        list.addAll(wqgoods.getLoopqishu());
                        mRecycler.setAdapter(adapter);
                        adapter.addList(list);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

}
