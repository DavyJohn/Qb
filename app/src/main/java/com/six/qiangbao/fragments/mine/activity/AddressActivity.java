package com.six.qiangbao.fragments.mine.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.saint.netlibrary.APIService;
import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.AddressDataItem;
import com.saint.netlibrary.model.Avatar;
import com.saint.netlibrary.utils.ConstantUtil;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.mine.adapter.AddressAdapter;
import com.six.qiangbao.utils.DividerDecoration;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cz.msebera.android.httpclient.Header;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class AddressActivity extends BaseActivity {

    @BindView(R.id.address_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.address_bar)
    Toolbar mToolBAR;
    @BindView(R.id.address_swip)
    SwipeRefreshLayout mSwip;

    private List<AddressDataItem> data = new ArrayList<>();
    private AddressAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_main_layout);
        setUptoolBar();
        initView();
        alladdress();
    }

    private void initView(){
        mSwip.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

        mRecycler.setHasFixedSize(true);
        addItemSwipeHelper(mRecycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerDecoration(AddressActivity.this,LinearLayoutManager.VERTICAL));
        adapter = new AddressAdapter(AddressActivity.this);
        adapter.setOnItemClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion) {
                String id = data.get(postion).getId();
                moren(id);
            }
        });

    }

    private void initdata(){
        mSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                alladdress();
            }
        });
    }

    private void alladdress(){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.alladdress()
                .subscribe(newSubscriber(new Action1<List<AddressDataItem>>() {
                    @Override
                    public void call(List<AddressDataItem> addressDataItems) {
                        if (mSwip.isRefreshing()) mSwip.setRefreshing(false);
                        data.clear();
                        System.out.print(addressDataItems);
                        if (addressDataItems.size() == 0){
                            mRecycler.setVisibility(View.GONE);
                        }else {
                            data.addAll(addressDataItems);
                            mRecycler.setAdapter(adapter);
                            adapter.addData(addressDataItems);
                        }


                    }
                }));
        mCompositeSubscription.add(subscription);

    }


    private void moren(final String id){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.moren(id)
                .subscribe(newSubscriber(new Action1<Avatar>() {
                    @Override
                    public void call(Avatar avatar) {
                        System.out.print(avatar);
                        if (avatar.getRst() == String.valueOf(0)){
                            ConstantUtil.MOREN_ID = Integer.parseInt(id);
                            alladdress();
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    private void setUptoolBar(){
        mToolBAR.setTitle("");
        setSupportActionBar(mToolBAR);
        mToolBAR.setNavigationIcon(R.drawable.left_icon);
        mToolBAR.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void addItemSwipeHelper(RecyclerView recyclerView) {
        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false; //no drag-n-drop
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();//postion
                // TODO: 2016/8/3
                String id = data.get(index).getId();
                del(index,String.valueOf(id));
                adapter.notifyItemRemoved(index);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                getDefaultUIUtil().clearView(((AddressAdapter.ViewHolder) viewHolder).vItem);
                ((AddressAdapter.ViewHolder) viewHolder).vBackground.setBackgroundColor(Color.TRANSPARENT);

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (viewHolder != null) {
                    getDefaultUIUtil().onSelected(((AddressAdapter.ViewHolder) viewHolder).vItem);
                }
            }

            @Override
            public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                getDefaultUIUtil().onDraw(canvas, recyclerView, ((AddressAdapter.ViewHolder) viewHolder).vItem, dX, dY, actionState, isCurrentlyActive);
                if (dX < 0) { // 向左滑动是的提示
                    ((AddressAdapter.ViewHolder) viewHolder).vBackground.setBackgroundResource(R.color.red);
                }
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView);
    }


    private void del(final int postion, String id){
        final ApiWrapper wrapper = new ApiWrapper();
            Subscription subscription = wrapper.del(id)
                    .subscribe(newSubscriber(new Action1<Avatar>() {
                        @Override
                        public void call(Avatar avatar) {
                            System.out.print(avatar);
                            adapter.onItemDismiss(postion);
                            data.remove(postion);
                        }
                    }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add){
            Intent intent = new Intent(this,AddAddressActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initdata();
    }
}
