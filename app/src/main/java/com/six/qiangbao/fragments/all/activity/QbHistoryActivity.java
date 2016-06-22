package com.six.qiangbao.fragments.all.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.ShopDetails;
import com.saint.netlibrary.model.ShopDetailsQData;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.all.adapter.QbHistoryAdapter;
import com.six.qiangbao.utils.ConstantUtil;
import com.six.qiangbao.utils.DividerDecoration;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/22.
 */
public class QbHistoryActivity extends BaseActivity {

    @Bind(R.id.qiangbao_history_bar)
    Toolbar mToolbar;
    @Bind(R.id.history_recycler)
    RecyclerView mRecycler;
    private String id;
    private List<ShopDetailsQData>  list = new ArrayList<>();
    private QbHistoryAdapter adapter;
    private String url ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qiangbao_history_main_layout);
        id = getIntent().getStringExtra("shop_id");
        setUpToolabr();
        initdata();
    }

    private void initdata(){
        shopDetails();
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerDecoration(this));
        adapter = new QbHistoryAdapter(this);
        mRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new QbHistoryAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion) {
                if (postion == 0){
                    finish();
                }else {
                    url = list.get(postion).url;
                    shopUrl(com.saint.netlibrary.utils.ConstantUtil.API_HOST+ url);
                }

            }
        });

    }

    private void setUpToolabr(){
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

    private void shopDetails(){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.shopDetails(String.valueOf(id))
                .subscribe(newSubscriber(new Action1<ShopDetails>() {
                    @Override
                    public void call(ShopDetails shopDetails) {
                        list.clear();
                        adapter.addList(shopDetails.loopqishu);
                        list.addAll(shopDetails.loopqishu);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    private void shopUrl(String url){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.shopUrl(url)
                .subscribe(newSubscriber(new Action1<ShopDetails>() {
                    @Override
                    public void call(ShopDetails shopDetails) {
                        String id= shopDetails.getItem().id;
                        System.out.print(id);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
