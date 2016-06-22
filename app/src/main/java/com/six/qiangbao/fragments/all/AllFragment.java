package com.six.qiangbao.fragments.all;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.saint.netlibrary.APIService;
import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.CarList;
import com.saint.netlibrary.model.ShopListData;
import com.six.qiangbao.BaseFragment;
import com.six.qiangbao.R;
import com.six.qiangbao.ShopDetialActivity;
import com.six.qiangbao.activitys.MainActivity;
import com.six.qiangbao.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by yyx on 16/5/20.
 */
public class AllFragment extends BaseFragment {


    private AllAdapter adapter;
    @Bind(R.id.all_toolbar)
    Toolbar toolbar;
    @Bind(R.id.all_swipe)
    SwipeRefreshLayout mSwipe;
    @Bind(R.id.all_recycler)
    RecyclerView mRecycler;
    @Bind(R.id.dropMenu)
    DropDownMenu mMenu;
    private final String[] headers = new String[]{"全部分类", "最新"};
    private final String[] header_ten = new String[]{"十元专区", "最新"};
    private List<String> mListtype = new LinkedList<>();
    private List<String> mListTypeId= new LinkedList<>();
    private String[] type;
    private String[] px = new String[]{"最新","人气","热度","价值（由高到低）","价值（由低到高）"};
    private String[] pxId = new String[]{"40","20","10","50","60"};
    private List<ShopListData> listDatas = new ArrayList<>();
    private String menu1 = "list";
    private String menu2 =String.valueOf(40) ;
    private int page = 0;
    private String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.all_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        setToolabr();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        initCatlist();
        shopList();
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                shopList();
            }
        });

        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerDecoration(getActivity()));
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingTolast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (lastItem == (totalItemCount - 1) && isSlidingTolast) {
                        ++page;
                        shopList();
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
        adapter = new AllAdapter(getActivity());
        mRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new AllAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), ShopDetialActivity.class);
                intent.putExtra("shop_id", listDatas.get(postion).id);
                startActivity(intent);
            }
        });
    }

    /**
     * 商品分类
     */
    private void initCatlist() {
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.carList()
                .subscribe(newSubscriber(new Action1<List<CarList>>() {
                    @Override
                    public void call(List<CarList> carLists) {
                        mListtype.add(0, "全部分类");
                        mListTypeId.add(0, "list");
                        for (int i = 1; i < carLists.size() + 1; i++) {
                            mListtype.add(i, carLists.get(i - 1).name);
                            mListTypeId.add(i, carLists.get(i - 1).cateid);
                        }
                        type = new String[mListtype.size()];
                        for (int m =0;m<mListtype.size();m++){
                            type[m] = mListtype.get(m);
                        }
                        System.out.print(type);
                        initDropMenu();//初始化筛选menu
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    private void initDropMenu() {

        mMenu.setShowDivider(true);
        mMenu.setmMenuListBackColor(Color.WHITE);
        mMenu.setmMenuCount(2);//Menu的个数
        mMenu.setmShowCount(6);//Menu展开list数量太多是只显示的个数
        mMenu.setShowCheck(true);//是否显示展开list的选中项
        mMenu.setmMenuTitleTextSize(14);//Menu的文字大小
        mMenu.setmMenuTitleTextColor(Color.BLACK);//Menu的文字颜色
        mMenu.setmMenuListTextSize(16);//Menu展开list的文字大小
        mMenu.setmMenuListTextColor(Color.BLACK);//Menu展开list的文字颜色
        mMenu.setmMenuBackColor(Color.WHITE);//Menu的背景颜色
        mMenu.setmMenuPressedBackColor(Color.LTGRAY);//Menu按下的背景颜色
        mMenu.setmCheckIcon(R.drawable.xuanzhong);//Menu展开list的勾选图片
        mMenu.setmUpArrow(R.drawable.arrow_up);//Menu默认状态的箭头
        mMenu.setmDownArrow(R.drawable.arrow_down);//Menu按下状态的箭头
        mMenu.setDefaultMenuTitle(headers);//默认未选择任何过滤的Menu title
        mMenu.setMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            //Menu展开的list点击事件  RowIndex：list的索引  ColumnIndex：menu的索引
            public void onSelected(View listview, int RowIndex, int ColumnIndex) {
                if (ColumnIndex == 0) {
                    menu1 = mListTypeId.get(RowIndex);
                    Toast.makeText(getActivity(),menu1,Toast.LENGTH_SHORT).show();
                } else if (ColumnIndex == 1) {
                    menu2 = pxId[RowIndex];
                    Toast.makeText(getActivity(),menu2,Toast.LENGTH_SHORT).show();
                }
                shopList();
            }
        });
        List<String[]> menuItems = new ArrayList<String[]>();
        menuItems.add(type);
        menuItems.add(px);
        mMenu.setmMenuItems(menuItems);
    }

    private void setToolabr() {
        toolbar.setTitle("");
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void shopList() {
        String cat = menu1;//返回全部可更改
        String sel = menu2;//paixid
        final int nextPage = page + 1;//有大问题
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.shopList(cat, sel, String.valueOf(nextPage))
                .subscribe(newSubscriber(new Action1<List<ShopListData>>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void call(List<ShopListData> shopListDatas) {
                            listDatas.clear();
                            mSwipe.setRefreshing(false);
                            adapter.addData(shopListDatas);
                            listDatas.addAll(shopListDatas);
                            for (int i = 0; i < shopListDatas.size(); i++) {
                                String text = shopListDatas.get(0).page;
                                Log.e("AllFragment==============",text);
                                if (text.equals(null)){
                                    Toast.makeText(getActivity(),"没有更多的数据了...",Toast.LENGTH_SHORT).show();
                                }
                            }

                    }
                }));
        mCompositeSubscription.add(subscription);
    }


}
