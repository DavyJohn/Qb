package com.six.qiangbao.fragments.newst;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.LatestAnnouncement;
import com.saint.netlibrary.model.ListItemsData;
import com.six.qiangbao.BaseFragment;
import com.six.qiangbao.R;
import com.six.qiangbao.activitys.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by yyx on 16/5/20.
 */
public class NewFragment extends BaseFragment {

    @Bind(R.id.new_toolbar)Toolbar toolbar;
    @Bind(R.id.newst_swipe)SwipeRefreshLayout mSwipe;
    @Bind(R.id.newst_recycler)
    RecyclerView mRecycler;

    private List<ListItemsData> list = new ArrayList<>();
    private NewstAdapter adapter ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.new_fragment_layout,container,false);
        ButterKnife.bind(this,rootView);
        toolbar.setTitle("");
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        Announcement();
    }

    private void initData(){
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(),"下拉刷新",Toast.LENGTH_SHORT).show();

            }
        });

        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NewstAdapter(context);
        mRecycler.setAdapter(adapter);


    }

    private void Announcement(){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.announcement(String.valueOf(0),String.valueOf(10))
                .subscribe(newSubscriber(new Action1<LatestAnnouncement>() {
                    @Override
                    public void call(LatestAnnouncement latestAnnouncement) {
                        list.addAll(latestAnnouncement.listItems);
                        adapter.addData(list);

                    }
                }));
        mCompositeSubscription.add(subscription);

    }
}
