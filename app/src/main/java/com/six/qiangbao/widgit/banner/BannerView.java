package com.six.qiangbao.widgit.banner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.saint.netlibrary.model.BannerData;
import com.saint.netlibrary.model.BannerListItems;
import com.six.qiangbao.R;
import com.six.qiangbao.utils.DisplayUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * 自定义Banner无限轮播控件
 */
public class BannerView extends RelativeLayout implements BannerAdapter.ViewPagerOnItemClickListener
{

    @BindView(R.id.widget_banner_viewpager)
    ViewPager viewPager;

    @BindView(R.id.widget_banner_points_group)
    LinearLayout points;

    private CompositeSubscription compositeSubscription;

    //默认轮播时间，10s
    private int delayTime = 10;

    private List<ImageView> imageViewList;

    private BannerAdapter bannerAdapter;

    private Context context;

    private List<BannerListItems> bannerList;

    //选中显示Indicator
    private int selectRes = R.drawable.shape_dots_select;

    //非选中显示Indicator
    private int unSelcetRes = R.drawable.shape_dots_default;

    private ImageView hImageView;
    public BannerView(Context context)
    {

        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs)
    {

        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr)
    {

        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.widget_banner_layout, this, true);
        ButterKnife.bind(this);
        imageViewList = new ArrayList<>();
    }

    private LinearLayout.LayoutParams params;

    /**
     * 设置轮播间隔时间
     *
     * @param time 轮播间隔时间，单位秒
     */
    public BannerView delayTime(int time)
    {

        this.delayTime = time;
        return this;
    }

    /**
     * 设置Points资源 Res
     *
     * @param selectRes   选中状态
     * @param unselcetRes 非选中状态
     */
    public void setPointsRes(int selectRes, int unselcetRes)
    {

        this.selectRes = selectRes;
        this.unSelcetRes = unselcetRes;
    }

    /**
     * 图片轮播需要传入参数 url
     */
    public void build(List<BannerListItems> list)
    {
        destory();
        bannerList = new ArrayList<>();
        bannerList.clear();
        bannerList.addAll(list);
        if (list.size() == 0)
        {
            this.setVisibility(GONE);
            return;
        }
        final int pointSize;
        pointSize = bannerList.size();

        if (pointSize == 2)
        {
            bannerList.addAll(list);
        }
        //判断是否清空 指示器点
        if (points.getChildCount() != 0)
        {
            points.removeAllViewsInLayout();
        }

        //初始化与个数相同的指示器点
        for (int i = 0; i < pointSize; i++)
        {
            View dot = new View(context);
            dot.setBackgroundResource(unSelcetRes);
            params = new LinearLayout.LayoutParams(
                    DisplayUtil.dip2px(context, 5),
                    DisplayUtil.dip2px(context, 5));
            params.leftMargin = 10;
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            points.addView(dot);
        }

        points.getChildAt(0).setBackgroundResource(selectRes);

        String url;
        for ( int i = 0; i < bannerList.size(); i++)
        {hImageView = new ImageView(context);
            url = bannerList.get(i).getSrc();
            Picasso.with(context)
                    .load(url)
                    .config(Bitmap.Config.RGB_565)
                    .into(hImageView);
            imageViewList.add(hImageView);
        }


        //监听图片轮播，改变指示器状态
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int pos)
            {
                pos = pos % pointSize;
                for (int i = 0; i < points.getChildCount(); i++)
                {
                    points.getChildAt(i).setBackgroundResource(unSelcetRes);
                }
                points.getChildAt(pos).setBackgroundResource(selectRes);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

                switch (state)
                {
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (isStopScroll)
                        {
                            startScroll();
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        stopScroll();
                        compositeSubscription.unsubscribe();
                        break;
                }
            }
        });

        bannerAdapter = new BannerAdapter(imageViewList);
        viewPager.setAdapter(bannerAdapter);
        bannerAdapter.notifyDataSetChanged();
        bannerAdapter.setmViewPagerOnItemClickListener(this);

        //图片开始轮播
        startScroll();
    }

    private boolean isStopScroll = false;

    /**
     * 图片开始轮播
     */
    private void startScroll()
    {

        compositeSubscription = new CompositeSubscription();
        isStopScroll = false;
        Subscription subscription = Observable.timer(delayTime, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>()
                {

                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {


                    }

                    @Override
                    public void onNext(Long aLong)
                    {

                        if (isStopScroll)
                        {
                            return;
                        }
                        isStopScroll = true;
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                });
        compositeSubscription.add(subscription);
    }

    /**
     * 图片停止轮播
     */
    private void stopScroll()
    {
        isStopScroll = true;
    }

    public void destory()
    {
        if (compositeSubscription != null)
        {
            compositeSubscription.unsubscribe();
        }
    }

    /**
     * 设置ViewPager的Item点击回调事件
     *
     * @param position
     */
    @Override
    public void onItemClick(int position)
    {
        Toast.makeText(context,bannerList.get(position).getSrc()+"",Toast.LENGTH_SHORT).show();
    }
}
