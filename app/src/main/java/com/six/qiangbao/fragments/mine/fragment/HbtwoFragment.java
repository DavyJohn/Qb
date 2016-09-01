package com.six.qiangbao.fragments.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.HongbaoData;
import com.six.qiangbao.BaseFragment;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.mine.adapter.HongBaoAdapter;
import com.six.qiangbao.utils.DividerDecoration;

import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;


/**
 * Created by 志浩 on 2016/6/16.
 */
public class HbtwoFragment extends BaseFragment {

    @BindView(R.id.hb_one_recycler)
    RecyclerView mRecycler;
    private HongBaoAdapter adapter;
    private String order_money,used,valid,invalid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hb_one_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();

    }

    @Override
    protected void lazyLoad() {

    }

    private void initview(){
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        adapter = new HongBaoAdapter(getActivity());
    }


    private void data(String order_money,String used,String valid,String invalid){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.haobao(order_money,used,valid,invalid)
                .subscribe(newSubscriber(new Action1<List<HongbaoData>>() {
                    @Override
                    public void call(List<HongbaoData> hongbaoDatas) {
                        System.out.print(hongbaoDatas);
                        if (hongbaoDatas.size() !=0){
                            mRecycler.setAdapter(adapter);
                            adapter.data(hongbaoDatas);
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);

    }

    @Override
    public void onStart() {
        super.onStart();
        data(order_money,String.valueOf(1),valid,invalid);
    }
}
