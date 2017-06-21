package com.six.qb.fragments.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.ListItems;
import com.saint.netlibrary.model.MyQbJu;
import com.six.qb.BaseFragment;
import com.six.qb.R;
import com.six.qb.fragments.mine.adapter.AllFragmentAdapter;
import com.six.qb.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class IngFragment extends BaseFragment {

    private int status;
    @BindView(R.id.mine_all_recycler)
    RecyclerView mRecycler;
    private List<ListItems> list = new ArrayList<>();
    private AllFragmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.myju_fragmnet_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initview();
    }

    @Override
    protected void lazyLoad() {

    }


    private void initview(){
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        adapter = new AllFragmentAdapter(getActivity());


    }

    private void data(){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.ju(String.valueOf(0),String.valueOf(10),"dsada",String.valueOf(1))
                .subscribe(newSubscriber(new Action1<MyQbJu>() {
                    @Override
                    public void call(MyQbJu myQbJu) {
                        System.out.print(myQbJu);
                        list.clear();
                        list.addAll(myQbJu.getListItems());
                        if (list.size() != 0){
                            mRecycler.setAdapter(adapter);
                            adapter.data(list);
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onStart() {
        super.onStart();
        status = com.saint.netlibrary.utils.ConstantUtil.STATUS ;
        data();
    }
}
