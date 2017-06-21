package com.six.qb.fragments.newst;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.six.qb.BaseFragment;
import com.six.qb.R;
import com.six.qb.activitys.MainActivity;
import com.six.qb.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by yyx on 16/5/20.
 */
public class NewFragment extends BaseFragment {

    @BindView(R.id.new_toolbar)Toolbar toolbar;
    @BindView(R.id.newst_swipe)SwipeRefreshLayout mSwipe;
    @BindView(R.id.newst_recycler)
    RecyclerView mRecycler;


    private List<ListItemsData> list = new ArrayList<>();
    private NewstAdapter adapter ;

    private int star = 0;
    private int i = 0 ;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    announcement();
                    break;
            }
        }
    };

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
        thread.run();
        initView();
        initData();
    }


    private void initView(){
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        adapter = new NewstAdapter(context);
    }
    private void initData(){
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新并初始化
                i= 0;
                if (list == null){
                }else {
                    list.clear();
                }
                thread.run();
            }
        });


        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingTolast = false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (lastItem == (totalItemCount - 1) && isSlidingTolast) {
                            ++i;
                        thread.run();
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isSlidingTolast = true;
                } else {
                    isSlidingTolast = false;
                }
            }
        });

        adapter.setOnItemClickListener(new NewstAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(),ResultsRevealedActivity.class);
                intent.putExtra("id",list.get(postion).id);
                startActivity(intent);
            }
        });
    }
    /**
     * 获取最新揭晓
     * */
    private void announcement(){
        star = i*10;
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.announcement(String.valueOf(star),String.valueOf(10))
                .subscribe(newSubscriber(new Action1<LatestAnnouncement>() {
                    @Override
                    public void call(LatestAnnouncement latestAnnouncement) {
                        mSwipe.setRefreshing(false);
                        list.addAll(latestAnnouncement.getListItems());
                        if (list.size() != 0){
                            mRecycler.setAdapter(adapter);
                            adapter.addData(list);
                        }

                        String coun = latestAnnouncement.getCount();
                        if (i >Integer.parseInt(coun)/10){
                            Toast.makeText(getActivity(),"无更多数据",Toast.LENGTH_SHORT).show();
                        }

                    }
                }));
        mCompositeSubscription.add(subscription);

    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            Message message = new Message();
            message.what = 0;
            handler.sendMessage(message);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void lazyLoad() {

    }
}
