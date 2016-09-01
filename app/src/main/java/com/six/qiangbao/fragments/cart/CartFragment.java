package com.six.qiangbao.fragments.cart;

import android.content.Intent;
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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.AddShopCarResult;
import com.saint.netlibrary.model.DelCartItemResult;
import com.saint.netlibrary.model.Login;
import com.saint.netlibrary.model.PayForResultData;
import com.saint.netlibrary.utils.ConstantUtil;
import com.six.qiangbao.BaseFragment;
import com.six.qiangbao.R;
import com.six.qiangbao.activitys.MainActivity;
import com.six.qiangbao.activitys.PaymentActivity;
import com.six.qiangbao.fragments.cart.adapter.CartAdapter;
import com.six.qiangbao.utils.DividerDecoration;
import com.six.qiangbao.utils.RealmTool;
import com.six.qiangbao.utils.ShopCartData;


import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by yyx on 16/5/20.
 */
public class CartFragment extends BaseFragment implements View.OnClickListener {

    private boolean isisPrepared;
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
    private RealmList<ShopCartData> dataRealmList = new RealmList<ShopCartData>();
    private Realm realm = Realm.getDefaultInstance();
    private CartAdapter adapter;
    private String id;

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
        final RealmResults<ShopCartData> realmResults = realm.where(ShopCartData.class).findAll();
        dataRealmList.clear();
        dataRealmList.addAll(realmResults);
        if (dataRealmList.size() != 0){
            int count = 0;
            int total = 0;
            mLayout.setVisibility(View.VISIBLE);
            for (int i= 0;i<dataRealmList.size();i++){
                int s1 = Integer.parseInt(dataRealmList.get(i).getGonum());
                int s2 = (int) Double.parseDouble(dataRealmList.get(i).getMoney());
                System.out.print(s2);
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
        adapter.data(dataRealmList);
        if (mSwip.isRefreshing())mSwip.setRefreshing(true);
        adapter.setOnAddClickListener(new CartAdapter.OnAddClickListener() {
            @Override
            public void OnAddClick(View view, int postion,TextView text) {
                realm.beginTransaction();
                RealmResults<ShopCartData> results = realm.where(ShopCartData.class).equalTo("id", dataRealmList.get(postion).getId()).findAll();
                id = results.get(0).getId();
                if (results.size() != 0) {
                    results.get(0).setGonum(String.valueOf(Integer.parseInt(results.get(0).getGonum()) + 1));
                    addcarnum(id,String.valueOf(Integer.parseInt(results.get(0).getGonum())));

                }
                realm.commitTransaction();
                data();
            }

        });

        adapter.setOnRemoveClickListener(new CartAdapter.OnRemoveClickListener() {
            @Override
            public void OnRemoveClick(View view, int postion) {
                realm.beginTransaction();
                final RealmResults<ShopCartData> results = realm.where(ShopCartData.class).equalTo("id", dataRealmList.get(postion).getId()).findAll();
                id = results.get(0).getId();
//
                if (results.size() != 0) {
                    if (String.valueOf(Integer.parseInt(results.get(0).getGonum()) ) == String.valueOf(1)) {
                            Toast.makeText(getActivity(),"删除商品",Toast.LENGTH_SHORT).show();
//                            delcart(id);
                    } else {
                        results.get(0).setGonum(String.valueOf(Integer.parseInt(results.get(0).getGonum()) - 1));
                        addcarnum(id,String.valueOf(Integer.parseInt(results.get(0).getGonum())));
                    }
                }
                realm.commitTransaction();
                data();
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
                            Toast.makeText(context, "加入成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    private void delcart(String id){
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
