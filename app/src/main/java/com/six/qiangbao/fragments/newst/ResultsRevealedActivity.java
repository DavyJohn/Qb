package com.six.qiangbao.fragments.newst;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.LatestAnnouncement;
import com.saint.netlibrary.model.ShopDetails;
import com.saint.netlibrary.model.WqGoods;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.activitys.MainActivity;
import com.six.qiangbao.activitys.ShopDetialActivity;
import com.six.qiangbao.fragments.all.activity.AllRecordActivity;
import com.six.qiangbao.utils.ConstantUtil;
import com.six.qiangbao.utils.DateUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/21.
 */
public class ResultsRevealedActivity extends BaseActivity {

    @BindView(R.id.results_bar)
    Toolbar mToolBar;
    @BindView(R.id.btn_results)
    Button mBtn;
    @BindView(R.id.result_shop_image)
    ImageView mImageShop;
    @BindView(R.id.results_user_image)
    ImageView mImageUser;
    @BindView(R.id.results_username)
    TextView mTextUser;
    @BindView(R.id.results_user_address)
    TextView mTextUserAddress;
    @BindView(R.id.results_time)
    TextView mTextResultTime;
    @BindView(R.id.results_qb_time)
    TextView mTextQBTime;
    @BindView(R.id.qiangbao_renshu)
    TextView mTextRenshu;
    @BindView(R.id.qishu)
    TextView mTextQs;
    @BindView(R.id.qb_code)
    TextView mTextCode;

    @BindView(R.id.qb_time2)
    TextView mqbtime;
    @BindView(R.id.qb_code2)
    TextView mQCode;
    @OnClick(R.id.all_qbju) void all(){
        Intent intent = new Intent(this, AllRecordActivity.class);
        intent.putExtra("goods_id",id);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.shop_car) void  cart (){
        MainActivity.showFragment(3);
        MainActivity.bottomNavigation.setCurrentItem(3);
        finish();
    }
    @OnClick(R.id.btn_results) void back(){
        Intent intent = new Intent(this, ShopDetialActivity.class);
        intent.putExtra("shop_id",id);
        startActivity(intent);
        finish();
    }
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_revealed_main_layout);
        setUpToolBar();
        id = getIntent().getStringExtra("id");
        data();
    }

    private void setUpToolBar() {
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.left_icon);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void data() {
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.wqgoods(id)
                .subscribe(newSubscriber(new Action1<WqGoods>() {
                    @Override
                    public void call(WqGoods shopDetails) {
                        System.out.print(shopDetails);
                        Picasso.with(ResultsRevealedActivity.this).load(ConstantUtil.IMAGE_HEAD + shopDetails.getItem().thumb).into(mImageShop);
                        String url = ConstantUtil.IMAGE_HEAD+shopDetails.getGorecode().getUphoto();
                        Picasso.with(ResultsRevealedActivity.this).load(url).into(mImageUser);
                        mTextUser.setText(shopDetails.getGorecode().getUsername());
                        mTextUserAddress.setText(shopDetails.getGorecode().getIp());
                        double endtime =Double.parseDouble(shopDetails.getItem().q_end_time);
                        double startime = Double.parseDouble(shopDetails.getItem().time);
                        String start  = DateUtil.formatData("yyyy-MM-dd HH:mm:ss",startime);
                        String resule = DateUtil.formatData("yyyy-MM-dd HH:mm:ss",endtime);
                        mTextResultTime.setText(resule);
                        mTextQBTime.setText(start);
                        mTextQs.setText("第" + shopDetails.getItem().qishu + "幸运抢宝码：");
                        mTextCode.setText(shopDetails.getItem().q_user_code);
                        mTextRenshu.setText("商品获得者本期共抢宝" + shopDetails.getItem().canyurenshu + "人次");
                        mqbtime.setText(start);
                        mQCode.setText(shopDetails.getGorecode().getGoucode());
                        mBtn.setText("第" + shopDetails.getLoopqishu().size() + "期正在进行...");

                    }
                }));
        mCompositeSubscription.add(subscription);
    }


}
