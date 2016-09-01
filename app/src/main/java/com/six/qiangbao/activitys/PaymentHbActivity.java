package com.six.qiangbao.activitys;

import android.content.Intent;
import android.mtp.MtpConstants;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.HongbaoData;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.cart.adapter.PaymentHbAdapter;
import com.six.qiangbao.fragments.mine.adapter.HongBaoAdapter;
import com.six.qiangbao.utils.ConstantUtil;
import com.six.qiangbao.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/8/25.
 */
public class PaymentHbActivity extends BaseActivity {
    @BindView(R.id.payment_hb_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.payment_hb_bar)
    Toolbar mToolbar;
    private Double order_money;
    private String used,valid,invalid;
    private PaymentHbAdapter adapter;
    private List<HongbaoData> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_hb_main_layout);
        order_money = getIntent().getDoubleExtra("order_money",0);
        initview();
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

    private void initview(){
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerDecoration(this,LinearLayoutManager.VERTICAL));
        adapter= new PaymentHbAdapter(this);
        adapter.setOnItemClickListener(new PaymentHbAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion) {
                ConstantUtil.TAG = 1;//说明
                Intent intent = new Intent(mContext,PaymentActivity.class);
                intent.putExtra("money",data.get(postion).getMoney());//红包金额
                intent.putExtra("id",data.get(postion).getId());//红包的id
                startActivity(intent);
                finish();
            }
        });

    }

    private void data(String order_money,String used,String valid,String invalid){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.haobao(order_money,used,valid,invalid)
                .subscribe(newSubscriber(new Action1<List<HongbaoData>>() {
                    @Override
                    public void call(List<HongbaoData> hongbaoDatas) {
                        System.out.print(hongbaoDatas);
                        mRecycler.setAdapter(adapter);
                        if (hongbaoDatas.size() !=0 ){
                            data.addAll(hongbaoDatas);
                            adapter.data(hongbaoDatas);
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    protected void onStart() {
        super.onStart();
        data(String.valueOf(order_money),used,String.valueOf(1),invalid);
    }
}
