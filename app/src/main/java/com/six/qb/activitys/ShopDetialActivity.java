package com.six.qb.activitys;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.AddShopCarResult;
import com.saint.netlibrary.model.ShopDetails;
import com.saint.netlibrary.model.WqGoods;
import com.six.qb.BaseActivity;
import com.six.qb.R;
import com.six.qb.fragments.all.activity.AllRecordActivity;
import com.six.qb.fragments.all.activity.GraphicDetailsActivity;
import com.six.qb.fragments.all.activity.QbHistoryActivity;
import com.six.qb.utils.AppManager;
import com.six.qb.utils.ConstantUtil;
import com.six.qb.utils.DateUtil;
import com.six.qb.utils.SqliteTool;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/17.
 */
public class ShopDetialActivity extends BaseActivity  {

    @BindView(R.id.all_root)
    RelativeLayout mRoot;
    @BindView(R.id.shop_detail_bar)
    Toolbar mToolBar;
    @BindView(R.id.shop_details_image)
    ImageView mImage;
    @BindView(R.id.shop_detail_title)
    TextView mTextTitle;
    @BindView(R.id.shop_datail_price)
    TextView mTextPrice;
    @BindView(R.id.shop_detail_progress)
    ProgressBar mBarDetail;
    @BindView(R.id.shop_detail_canyu_number)
    TextView mTextCan;
    @BindView(R.id.shop_detail_all_number)
    TextView mTextAllp;
    @BindView(R.id.shop_detail_shenyu_number)
    TextView mTextShenp;
    @BindView(R.id.zongqb_text)
    TextView mTextZCS;
    @BindView(R.id.huojiang_image)
    ImageView mHeadImage;
    @BindView(R.id.guwu_image)
    ImageView mGW;
    @BindView(R.id.huojiang_name)
    TextView mHuojiangName;
    @BindView(R.id.huojiang_address)
    TextView mHuojiangAddress;
    @BindView(R.id.jiexiao_time)
    TextView mJiexiaoTime;
    @BindView(R.id.qb_time)
    TextView mQbTime;
    @BindView(R.id.qb_code)
    TextView mQbCodeText;
    @OnClick(R.id.qiagbao_jilu) void ju(){
        Intent intent = new Intent(this,AllRecordActivity.class);
        intent.putExtra("goods_id",id);
        startActivity(intent);
    }

    @OnClick(R.id.tuwen_detail) void  tw(){
        Intent intent = new Intent(this, GraphicDetailsActivity.class);
        intent.putExtra("content",content);
        startActivity(intent);
    }

    @OnClick(R.id.qiangbao_history) void his(){
        Intent intent = new Intent(this, QbHistoryActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.qiangbao_btn) void qb(){
        //先将商品放到购物车再跳转到 购物车界面
        ConstantUtil.isNEW = 1;
        addAnimation(mImage);
        SqliteTool.getInstance().addData(data.getItem().title,data.getItem().thumb,1,Integer.parseInt(data.getItem().id),data.getItem().yunjiage,mContext);
//        RealmTool.getInstance().realmdata(data.getItem(),String.valueOf(1));
    }

    @OnClick(R.id.guwu_btn) void joincar(){
        ConstantUtil.isNEW = 0;
        addAnimation(mImage);
        SqliteTool.getInstance().addData(data.getItem().title,data.getItem().thumb,1,Integer.parseInt(data.getItem().id),data.getItem().yunjiage,mContext);
    }

    @OnClick(R.id.guwu_image) void carImage(){
        MainActivity.showFragment(3);
        MainActivity.bottomNavigation.setCurrentItem(3);
        finish();
    }
    private String id,content,sid ,url,lasturl;
    private ShopDetails data;
    private int i = 0;
    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_detial_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        setUpToolBar();
        id = getIntent().getStringExtra("shop_id");
        sid = getIntent().getStringExtra("sid");

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
                        data = shopDetails;
                        lasturl = com.saint.netlibrary.utils.ConstantUtil.API_HOST+shopDetails.getLoopqishu().get(1).url;
                        url = ConstantUtil.IMAGE_HEAD+shopDetails.getItem().thumb;
                        Picasso.with(ShopDetialActivity.this).load(ConstantUtil.IMAGE_HEAD+shopDetails.getItem().thumb).into(mImage);
                        mTextTitle.setText(shopDetails.getItem().title);
                        mTextPrice.setText("价值"+shopDetails.getItem().money);
                        mBarDetail.setMax(Integer.parseInt(shopDetails.getItem().zongrenshu));
                        mBarDetail.setProgress(Integer.parseInt(shopDetails.getItem().canyurenshu));
                        mTextCan.setText(shopDetails.getItem().canyurenshu);
                        mTextAllp.setText(shopDetails.getItem().zongrenshu);
                        mTextShenp.setText(shopDetails.getItem().shenyurenshu);
                        content = shopDetails.getItem().content;
                        mHuojiangName.setText(shopDetails.getLastgorecode().getUsername());
                        mHuojiangAddress.setText(shopDetails.getLastgorecode().getIp());
                        mQbCodeText.setText(shopDetails.getLastgorecode().getHuode());
                        Picasso.with(ShopDetialActivity.this).load(ConstantUtil.IMAGE_HEAD+shopDetails.getLastgorecode().getUphoto()).into(mHeadImage);
                        lastdata();
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    /**
     * 获取上期获奖者信息*/
    private void lastdata(){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.shopUrl(lasturl)
                .subscribe(newSubscriber(new Action1<WqGoods>() {
                    @Override
                    public void call(WqGoods wqGoods) {
                        System.out.print(wqGoods);
                        Picasso.with(mContext).load(ConstantUtil.IMAGE_HEAD+wqGoods.getGorecode().getUphoto()).into(mHeadImage);
                        mTextZCS.setText("总抢宝"+wqGoods.getGorecode().getGonumber()+"人次");
                        mHuojiangName.setText(wqGoods.getGorecode().getUsername());
                        mHuojiangAddress.setText(wqGoods.getGorecode().getIp());
                        Double time = Double.parseDouble(wqGoods.getItem().q_end_time);
                        Double start = Double.parseDouble(wqGoods.getItem().time);
                        String  result = DateUtil.formatData("yyyy-MM-dd hh:mm:ss",time);
                        mJiexiaoTime.setText(result);
                        mQbTime.setText(DateUtil.formatData("yyyy-MM-dd hh:mm:ss",start));
                        mQbCodeText.setText(wqGoods.getGorecode().getHuode());
                    }
                }));
        mCompositeSubscription.add(subscription);
    }


    private void addcart(String id){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.addcar(id,String.valueOf(1))
                .subscribe(newSubscriber(new Action1<AddShopCarResult>() {
                    @Override
                    public void call(AddShopCarResult addShopCarResult) {
                        if (addShopCarResult.getCode()  == String.valueOf(0)){
                            Toast.makeText(getApplicationContext(),"加入购物测成功",Toast.LENGTH_SHORT).show();

                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    private void addAnimation(ImageView image){
        final ImageView goods = new ImageView(ShopDetialActivity.this);
        Picasso.with(ShopDetialActivity.this).load(url).into(goods);
        RelativeLayout.LayoutParams params  = new RelativeLayout.LayoutParams(100,100);
        goods.setLayoutParams(params);
        mRoot.addView(goods);

        int[] parentLocation = new int[2];
        mRoot.getLocationInWindow(parentLocation);

        int startLoc[] = new int[2];
        image.getLocationInWindow(startLoc);

        int endLoc[] = new int[2];
        mGW.getLocationInWindow(endLoc);

        float startX = startLoc[0] - parentLocation[0] + image.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + image.getHeight() / 2;

        float toX = endLoc[0] - parentLocation[0] + mGW.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];

        Path path = new Path();

        path.moveTo(startX, startY);

        path.quadTo((startX + toX) / 2, startY, toX, toY);
        mPathMeasure = new PathMeasure(path, false);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });
        valueAnimator.start();
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }
            //当动画结束后：
            @Override
            public void onAnimationEnd(Animator animation) {
                // 购物车的数量加1
                i++;
//                count.setText(String.valueOf(i));
                Log.e("数量",String.valueOf(i));
//                // 把移动的图片imageview从父布局里移除
                mRoot.removeView(goods);

                if (ConstantUtil.isNEW == 1){
                    MainActivity.showFragment(3);
                    MainActivity.bottomNavigation.setCurrentItem(3);
                    finish();
                }
                addcart(id);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        shopDetails();
    }
}
