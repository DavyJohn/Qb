package com.six.qiangbao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.ShopDetails;
import com.saint.netlibrary.model.ShopDetailsQData;
import com.six.qiangbao.fragments.all.activity.AllRecordActivity;
import com.six.qiangbao.fragments.all.activity.GraphicDetailsActivity;
import com.six.qiangbao.fragments.all.activity.QbHistoryActivity;
import com.six.qiangbao.utils.ConstantUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/17.
 */
public class ShopDetialActivity extends BaseActivity {

    @Bind(R.id.shop_detail_bar)
    Toolbar mToolBar;
    @Bind(R.id.shop_details_image)
    ImageView mImage;
    @Bind(R.id.shop_detail_title)
    TextView mTextTitle;
    @Bind(R.id.shop_datail_price)
    TextView mTextPrice;
    @Bind(R.id.shop_detail_progress)
    ProgressBar mBarDetail;
    @Bind(R.id.shop_detail_canyu_number)
    TextView mTextCan;
    @Bind(R.id.shop_detail_all_number)
    TextView mTextAllp;
    @Bind(R.id.shop_detail_shenyu_number)
    TextView mTextShenp;
    @Bind(R.id.zongqb_text)
    TextView mTextZCS;
    @Bind(R.id.huojiang_image)
    ImageView mHeadImage;
    @Bind(R.id.huojiang_name)
    TextView mHuojiangName;
    @Bind(R.id.huojiang_address)
    TextView mHuojiangAddress;
    @Bind(R.id.jiexiao_time)
    TextView mJiexiaoTime;
    @Bind(R.id.qb_time)
    TextView mQbTime;
    @Bind(R.id.qb_code)
    TextView mQbCodeText;
    @OnClick(R.id.qiagbao_jilu) void ju(){
        startActivity(AllRecordActivity.class);
    }

    @OnClick(R.id.tuwen_detail) void  tw(){
        Intent intent = new Intent(this, GraphicDetailsActivity.class);
        intent.putExtra("content",content);
        startActivity(intent);
    }

    @OnClick(R.id.qiangbao_history) void his(){
        Intent intent = new Intent(this, QbHistoryActivity.class);
        intent.putExtra("shop_id",id);
        startActivity(intent);
        Toast.makeText(this,"抢宝历史",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.qiangbao_btn) void qb(){
        Toast.makeText(this,"立即一元抢宝",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.guwu_btn) void joincar(){
        Toast.makeText(this,"加入购物车",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.guwu_image) void carImage(){
        Toast.makeText(this,"购物车",Toast.LENGTH_SHORT).show();
    }
    private String id,content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_detial_main_layout);
        setUpToolBar();
        id = getIntent().getStringExtra("shop_id");
        shopDetails();
    }

    private void setUpToolBar(){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shop_share,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.shop_share){
                Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void shopDetails(){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.shopDetails(String.valueOf(id))
                .subscribe(newSubscriber(new Action1<ShopDetails>() {
                    @Override
                    public void call(ShopDetails shopDetails) {
                        Picasso.with(ShopDetialActivity.this).load(ConstantUtil.IMAGE_HEAD+shopDetails.getItem().thumb).into(mImage);
                        mTextTitle.setText(shopDetails.getItem().title);
                        mTextPrice.setText("价值"+shopDetails.getItem().money);
                        mBarDetail.setMax(Integer.parseInt(shopDetails.getItem().zongrenshu));
                        mBarDetail.setProgress(Integer.parseInt(shopDetails.getItem().canyurenshu));
                        mTextCan.setText(shopDetails.getItem().canyurenshu);
                        mTextAllp.setText(shopDetails.getItem().zongrenshu);
                        mTextShenp.setText(shopDetails.getItem().shenyurenshu);
                        content = shopDetails.getItem().content;
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

}
