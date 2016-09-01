package com.six.qiangbao.fragments.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.XIaoFeiJilu;
import com.six.qiangbao.BaseFragment;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.mine.adapter.ZuRightAdapter;
import com.six.qiangbao.utils.DividerDecoration;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class ZuRightFragment extends BaseFragment {

    @BindView(R.id.zu_right_recycler)
    RecyclerView mRecycler;

    private ZuRightAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.zu_right_main_layout,container,false);
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
        mRecycler.addItemDecoration(new DividerDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        adapter = new ZuRightAdapter(getActivity());

    }

    private void data(){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.xiaofei(String.valueOf(0),String.valueOf(10))
                .subscribe(newSubscriber(new Action1<XIaoFeiJilu>() {
                    @Override
                    public void call(XIaoFeiJilu xIaoFeiJilu) {
                        System.out.print(xIaoFeiJilu);
                        if (xIaoFeiJilu.getListItems().size() != 0 ){
                            mRecycler.setAdapter(adapter);
                            adapter.data(xIaoFeiJilu.getListItems());
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onStart() {
        super.onStart();
        data();
    }
}
