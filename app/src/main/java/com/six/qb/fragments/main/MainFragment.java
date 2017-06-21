package com.six.qb.fragments.main;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.AddShopCarResult;
import com.saint.netlibrary.model.BannerData;
import com.saint.netlibrary.model.BannerListItems;

import com.saint.netlibrary.model.ShopListData;
import com.six.qb.BaseFragment;

import com.six.qb.R;
import com.six.qb.activitys.MainActivity;
import com.six.qb.activitys.ShopDetialActivity;
import com.six.qb.utils.ConstantUtil;
import com.six.qb.utils.DividerDecoration;
import com.six.qb.utils.RealmTool;
import com.six.qb.utils.SqliteTool;
import com.six.qb.widgit.banner.BannerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by yyx on 16/5/20.
 */
public class MainFragment extends BaseFragment   {
    @BindView(R.id.rootview)
    CoordinatorLayout mRoot;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout app;
    @BindView(R.id.main_fragment_swipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.main_fragment_recycler)
    RecyclerView mRecycler;
    private Realm realm;
    private int i = 0;
    private float[] mCurrentPosition = new float[2];
    private PathMeasure mPathMeasure;
    private TextView sel1, sel2, sel3, sel4, tab1, tab2, tab3, tab4;
    private BannerView banner;
    private int page = 1;
    private int sum;
    private String sel;
    public List<ShopListData> list = new ArrayList<>();
    //获取bannerview数据
    private List<BannerListItems> bannerListItemses = new ArrayList<BannerListItems>();

    private MainFragmentAdapter adapter;

    Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    bannerdata();
                    break;
                case 1:
                    shopList();
                    break;
            }
        }
    };

    Thread bannerThread = new Thread() {
        @Override
        public void run() {
            Message bannder = new Message();
            bannder.what = 0;
            mHander.sendMessage(bannder);
        }
    };

    Thread shopThread = new Thread() {
        @Override
        public void run() {
            Message shopMsg = new Message();
            shopMsg.what = 1;
            mHander.sendMessage(shopMsg);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        toolbar.setTitle("");
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        Log.e("shopThead====","onViewCreated执行");
    }

    private void initView() {
        sel = String.valueOf(20);
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        //
        mRecycler.setHasFixedSize(true);
        mRecycler.addItemDecoration(new DividerDecoration(getActivity(), LinearLayoutManager.HORIZONTAL));
        mRecycler.addItemDecoration(new DividerDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //初始化bannerview
        banner = (BannerView) app.findViewById(R.id.bannerview);

        sel1 = (TextView) app.findViewById(R.id.sel1);
        sel1.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        sel2 = (TextView) app.findViewById(R.id.sel2);
        sel3 = (TextView) app.findViewById(R.id.sel3);
        sel4 = (TextView) app.findViewById(R.id.sel4);

        tab1 = (TextView) app.findViewById(R.id.tab1);
        tab2 = (TextView) app.findViewById(R.id.tab2);
        tab3 = (TextView) app.findViewById(R.id.tab3);
        tab4 = (TextView) app.findViewById(R.id.tab4);

    }

    private void tabinitdata() {
        if (bannerListItemses.size() != 0) {
            banner.delayTime(5).build(bannerListItemses);
        }
    }

    private void initlistdata() {
        adapter = new MainFragmentAdapter(getActivity());
        mRecycler.setAdapter(adapter);
        if (list.size() != 0) {
            adapter.addDATA(list);
        }

        /**
         * 添加购物车
         * */
        adapter.setOnItemBtnClickListener(new MainFragmentAdapter.OnItemBtnClickListener() {
            @Override
            public void OnItemBtnClick(View view, int postion, ImageView image) {
                addcar(postion);
                addcaran(postion,image);
                RealmTool.getInstance().realmdata(list.get(postion),String.valueOf(1));
                //sqlite数据库
                SqliteTool.getInstance().addData(list.get(postion).title,list.get(postion).thumb,1,Integer.parseInt(list.get(postion).id),list.get(postion).yunjiage,getContext());
            }
        });

        adapter.setOnItemClickListener(new MainFragmentAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), ShopDetialActivity.class);
                intent.putExtra("shop_id", list.get(postion).id);
                intent.putExtra("sid", list.get(postion).sid);
                startActivity(intent);
            }
        });

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showFragment(1);
                MainActivity.bottomNavigation.setCurrentItem(1);
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showFragment(1);
                MainActivity.bottomNavigation.setCurrentItem(1);

            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showFragment(1);
                MainActivity.bottomNavigation.setCurrentItem(1);
            }
        });

        sel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleancolor();
                sel1.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                sel = String.valueOf(20);
                list.clear();
                shopThread.run();
            }
        });

        sel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleancolor();
                sel2.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                sel = String.valueOf(40);
                list.clear();
                shopThread.run();
            }
        });
        sel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleancolor();
                sel3.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                sel = String.valueOf(30);
                list.clear();
                shopThread.run();
            }
        });
        sel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleancolor();
                sel4.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                //更改升降
                if (ConstantUtil.isSecond == 0) {
                    sel4.setText("价格（升）");
                    sel = String.valueOf(60);
                    list.clear();
                    ConstantUtil.isSecond = 1;
                } else if (ConstantUtil.isSecond == 1) {
                    sel4.setText("价格（降）");
                    sel = String.valueOf(50);
                    list.clear();
                    ConstantUtil.isSecond = 0;
                }
                shopThread.run();
            }
        });
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                ConstantUtil.isClean = 1;
                page = 1;
                shopThread.run();

            }
        });

        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingTolast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (lastItem == (totalItemCount - 1) && isSlidingTolast) {
                        page++;
                        if (page <= sum) {
                            shopThread.run();
                        } else {
                            Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
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
    }

    /**
     * bannerview
     */
    private void bannerdata() {
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.bannerdata().subscribe(newSubscriber(new Action1<BannerData>() {
            @Override
            public void call(BannerData bannerData) {
                bannerListItemses.clear();
                bannerListItemses.addAll(bannerData.getListItems());
                if (bannerListItemses.size() != 0) {
                    tabinitdata();
                }
            }
        }));
        mCompositeSubscription.add(subscription);
    }

    private void shopList() {
        String cat = "list";//返回全部可更改
        String selnum = sel;//paixid
        final int nextPage = page;//有大问题
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.shopList(cat, selnum, String.valueOf(nextPage))
                .subscribe(newSubscriber(new Action1<List<ShopListData>>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void call(List<ShopListData> shopListDatas) {
                        Log.e("MainFragment===shoplist",shopListDatas+"");
                        if (mSwipe.isRefreshing()) mSwipe.setRefreshing(false);
                        list.addAll(shopListDatas);
                        if (list.size() != 0 ) {
                            sum = Integer.parseInt(shopListDatas.get(0).sum);
                            initlistdata();
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    /**
     * 添加购物车
     */
    private void addcar(int indext) {
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.addcar(String.valueOf(list.get(indext).id), String.valueOf(1))
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

    private void cleancolor() {
        sel1.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray));
        sel2.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray));
        sel3.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray));
        sel4.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray));
    }

    /**
     * 贝塞尔曲线实现
     */
    public void addcaran(int index,ImageView image ) {
        final ImageView goods = new ImageView(getActivity());
        String url = ConstantUtil.IMAGE_HEAD+list.get(index).thumb;
        Picasso.with(getActivity()).load(url).into(goods);
        RelativeLayout.LayoutParams params  = new RelativeLayout.LayoutParams(100,100);
        mRoot.addView(goods,params);
        int[] parentLocation = new int[2];
        mRoot.getLocationInWindow(parentLocation);

        int startLoc[] = new int[2];
        image.getLocationInWindow(startLoc);

        int endLoc[] = new int[2];
        ((MainActivity)getActivity()).bottomNavigation.getLocationInWindow(endLoc);

        float startX = startLoc[0] - parentLocation[0] + image.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + image.getHeight() / 2;

        float toX = endLoc[0] - parentLocation[0] + ((MainActivity)getActivity()).bottomNavigation.getWidth() / 5;
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
               // 把移动的图片imageview从父布局里移除
                mRoot.removeView(goods);

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
    public void onStart() {
        super.onStart();
        bannerThread.run();
        shopThread.run();
        Log.e("shopThead====","onstart执行");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ConstantUtil.isClean = 0;
        mHander.removeCallbacksAndMessages(null);
    }

    @Override
    protected void lazyLoad() {

    }

}
