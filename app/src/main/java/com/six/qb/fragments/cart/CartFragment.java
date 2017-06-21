package com.six.qb.fragments.cart;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.AddShopCarResult;
import com.saint.netlibrary.model.DelCartItemResult;
import com.six.qb.BaseFragment;
import com.six.qb.R;
import com.six.qb.activitys.MainActivity;
import com.six.qb.activitys.PaymentActivity;
import com.six.qb.fragments.cart.adapter.CartAdapter;
import com.six.qb.utils.DividerDecoration;
import com.six.qb.utils.MyContentProvider;
import com.six.qb.utils.ShopCartData;
import com.six.qb.utils.SqliteTool;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by yyx on 16/5/20.
 */
public class CartFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.lay_cart_total)
    RelativeLayout mLayout;
    @BindView(R.id.cart_count)
    TextView mCount;
    @BindView(R.id.cart_total)
    TextView mTotal;
    @BindView(R.id.cart_toolbar)
    Toolbar toolbar;
    @BindView(R.id.cart_swip)
    SwipeRefreshLayout mSwip;
    @BindView(R.id.cart_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.button)
    Button mBtn;
    private Cursor cursor;
    private List<ShopCartData> list = new ArrayList<>();
    private CartAdapter adapter;
    private static boolean isSuccess = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cart_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        toolbar.setTitle("");
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();
        cursor = getContext().getContentResolver().query(MyContentProvider.URI,null,null,null,null);
        while (cursor.moveToNext()){
            int initId = cursor.getInt(cursor.getColumnIndex("cartId"));
            Log.e("initid====",initId+"");
            int initNum = cursor.getInt(cursor.getColumnIndex("cartNumber"));
            Log.e("initid====",initNum+"");
            addcarnum(String.valueOf(initId),String.valueOf(initNum));
        }

    }

    @Override
    protected void lazyLoad() {

    }

    private void initview() {
        mBtn.setOnClickListener(this);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mSwip.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        adapter = new CartAdapter(getActivity());
        mSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data();
            }
        });

    }

    private void data() {
        //获取Sql 所有数据
        cursor = getContext().getContentResolver().query(MyContentProvider.URI,null,null,null,null);
        list.clear();
        while (cursor.moveToNext()){
            ShopCartData data = new ShopCartData();
            data.setName(cursor.getString(cursor.getColumnIndex("cartName")));
            data.setImage(cursor.getString(cursor.getColumnIndex("cartImage")));
            data.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex("cartId"))));
            data.setGonum(String.valueOf(cursor.getInt(cursor.getColumnIndex("cartNumber"))));
            data.setMoney(String.valueOf(cursor.getInt(cursor.getColumnIndex("goodMoney"))));
            list.add(data);
        }
        if (list.size() != 0){
            int count = 0;
            int total = 0;
            mLayout.setVisibility(View.VISIBLE);
            for (int i= 0;i<list.size();i++){
                int s1 = Integer.parseInt(list.get(i).getGonum());
                int s2 = (int) Double.parseDouble(list.get(i).getMoney());
                count = count+s1;
                total = total+s1*s2;
            }
            mCount.setText("共"+String.valueOf(count)+"件商品");
            mTotal.setText("合计"+String.valueOf(total)+"抢宝币");
        }else {
            mLayout.setVisibility(View.GONE);
        }
        if (mSwip.isRefreshing()) mSwip.setRefreshing(false);
        mRecycler.setAdapter(adapter);
        adapter.sqlData(list);

        adapter.setOnAddClickListener(new CartAdapter.OnAddClickListener() {
            @Override
            public void OnAddClick(View view, int postion,TextView text) {
                SqliteTool.getInstance().addData(list.get(postion).getName(),list.get(postion).getImage(),Integer.parseInt(list.get(postion).getGonum())
                        ,Integer.parseInt(list.get(postion).getId()),list.get(postion).getMoney(),getActivity());
                data();//更新数据
                int num = Integer.parseInt(list.get(postion).getGonum());
                addcarnum(String.valueOf(list.get(postion).getId()),String.valueOf(num));

            }

        });
        adapter.setOnRemoveClickListener(new CartAdapter.OnRemoveClickListener() {
            @Override
            public void OnRemoveClick(View view, int postion,TextView text) {
                SqliteTool.getInstance().subData(Integer.parseInt(list.get(postion).getId()),getActivity());
                String goodId = list.get(postion).getId();
                data();//更新数据
                int testNum =Integer.parseInt(text.getText().toString())-1;
                Log.e("测试数据testNum=",testNum+"'");
                if (list.size() != 0&& testNum != 0){
                    int num = Integer.parseInt(list.get(postion).getGonum());
                    addcarnum(String.valueOf(list.get(postion).getId()),String.valueOf(num));
                }else if (list.size() == 0 || testNum == 0 ){
                    delcart(goodId);
                    SqliteTool.getInstance().deleteData(Integer.parseInt(goodId),getActivity());
                    data();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            //支付
            Intent intent = new Intent(getActivity(), PaymentActivity.class);
            startActivity(intent);
        }
    }

    /**
     * get添加购物车
     */
    private void addcarnum(String id,String num) {
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.addcarnum(id,num,"cart")
                .subscribe(newSubscriber(new Action1<AddShopCarResult>() {
                    @Override
                    public void call(AddShopCarResult addShopCarResult) {
                        System.out.print(addShopCarResult);
                        if (addShopCarResult.getCode() == String.valueOf(0)) {
                            isSuccess = true;
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    private void delcart(String id){
        Log.e("delcart======id",id);
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.delcart(id)
                .subscribe(newSubscriber(new Action1<DelCartItemResult>() {
                    @Override
                    public void call(DelCartItemResult delCartItemResult) {
                        System.out.print(delCartItemResult);
                        if (delCartItemResult.getCode() == String.valueOf(0)){
                            Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onStart() {
        super.onStart();
        mSwip.setRefreshing(true);
        data();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
