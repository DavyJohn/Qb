package com.six.qiangbao.fragments.newst;

import android.content.Intent;
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

    private int star = 0;
    private int i = 0 ;

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
        announcement();
    }

    private void initData(){
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i= 0;
                announcement();
            }
        });

        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

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
                            announcement();
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
        adapter = new NewstAdapter(context);
        mRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new NewstAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(),ResultsRevealedActivity.class);
                intent.putExtra("shop_image",list.get(postion).thumb);
                intent.putExtra("username",list.get(postion).q_user);
                intent.putExtra("user_image",list.get(postion).userphoto);
                intent.putExtra("user_address",list.get(postion).q_uid);
                intent.putExtra("user_code",list.get(postion).q_user_code);
                intent.putExtra("qishu",list.get(postion).qishu);
                intent.putExtra("renshu",list.get(postion).gonumber);
                intent.putExtra("jiexiao",list.get(postion).q_end_time);
                startActivity(intent);
            }
        });
    }

    private void announcement(){
        star = i*10;
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.announcement(String.valueOf(star),String.valueOf(10))
                .subscribe(newSubscriber(new Action1<LatestAnnouncement>() {
                    @Override
                    public void call(LatestAnnouncement latestAnnouncement) {
                        list.clear();
                        mSwipe.setRefreshing(false);
                        list.addAll(latestAnnouncement.listItems);
                        adapter.addData(list);
                        String coun = latestAnnouncement.count;

                        if (i >Integer.parseInt(coun)/10){
                            Toast.makeText(getActivity(),"无更多数据",Toast.LENGTH_SHORT).show();
                        }

                    }
                }));
        mCompositeSubscription.add(subscription);

    }
}
