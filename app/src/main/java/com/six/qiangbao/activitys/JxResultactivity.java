package com.six.qiangbao.activitys;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.WqGoods;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.all.activity.AllRecordActivity;
import com.six.qiangbao.utils.AppManager;
import com.six.qiangbao.utils.ConstantUtil;
import com.six.qiangbao.utils.DateUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/8/19.
 */
public class JxResultactivity extends BaseActivity {

    private String url,id;
    @BindView(R.id.results_bar)
    Toolbar mToolbar;
    @BindView(R.id.result_shop_image)
    ImageView goodsView;
    @BindView(R.id.results_user_image)
    ImageView view;
    @BindView(R.id.results_username)
    TextView mOwn;
    @BindView(R.id.results_user_address)
    TextView mOwnAddress;
    @BindView(R.id.results_time)
    TextView mTextEndTime;
    @BindView(R.id.results_qb_time)
    TextView mTextTime;
    @BindView(R.id.qb_code)
    TextView mTextCode;
    @BindView(R.id.qb_time2)
    TextView mTextTime2;
    @BindView(R.id.qb_code2)
    TextView mTextCode2;
    @BindView(R.id.qiangbao_renshu)
    TextView mTextNum;
    @BindView(R.id.btn_results)
    Button btn3;

    @OnClick(R.id.btn_results) void back(){
        Intent intent = new Intent(this,ShopDetialActivity.class);
        intent.putExtra("shop_id",id);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.all_qbju) void click(){
        //id
        Intent intent = new Intent(this, AllRecordActivity.class);
        intent.putExtra("goods_id",id);
        startActivity(intent);
    }

    @OnClick(R.id.shop_car) void cart(){
        MainActivity.showFragment(3);
        MainActivity.bottomNavigation.setCurrentItem(3);
        finish();
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    shopUrl(url);
                    break;
            }
        }
    };

    Thread thread = new Thread() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 0;
            handler.sendMessage(message);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_revealed_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        toolbar();
        url = getIntent().getStringExtra("url");
    }

    private void toolbar(){
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


    private void shopUrl(String url) {
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.shopUrl(url)
                .subscribe(newSubscriber(new Action1<WqGoods>() {
                    @Override
                    public void call(WqGoods shopDetails) {
                        id = shopDetails.getItem().id;
                        Picasso.with(getApplicationContext()).load(ConstantUtil.IMAGE_HEAD + shopDetails.getItem().thumb).into(goodsView);
                        Picasso.with(getApplicationContext()).load(ConstantUtil.IMAGE_HEAD+shopDetails.getGorecode().getUphoto()).into(view);
                        mOwn.setText(shopDetails.getGorecode().getUsername());
                        mOwnAddress.setText(shopDetails.getGorecode().getIp());
                        double endtime =Double.parseDouble(shopDetails.getItem().q_end_time);
                        double startime = Double.parseDouble(shopDetails.getItem().time);
                        String start  = DateUtil.formatData("yyyy-MM-dd HH:mm:ss",startime);
                        String resule = DateUtil.formatData("yyyy-MM-dd HH:mm:ss",endtime);
                        mTextEndTime.setText(resule);
                        mTextTime.setText(start);
                        mTextCode.setText("("+shopDetails.getGorecode().getShopqishu()+")"+"幸运抢宝码："+shopDetails.getGorecode().getHuode());
                        mTextNum.setText("商品获得者本期总共抢宝"+shopDetails.getGorecode().getGonumber()+"次");
                        mTextTime2.setText(shopDetails.getGorecode().getTime());
                        mTextCode2.setText(shopDetails.getGorecode().getGoucode());
                        btn3.setText(shopDetails.getLoopqishu().get(0).title+"正在进行...");

                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    protected void onStart() {
        super.onStart();
        thread.run();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
