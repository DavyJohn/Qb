package com.six.qiangbao.fragments.all;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.six.qiangbao.BaseFragment;
import com.six.qiangbao.R;
import com.six.qiangbao.ShopDetialActivity;
import com.six.qiangbao.activitys.MainActivity;
import com.six.qiangbao.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yyx on 16/5/20.
 */
public class AllFragment extends BaseFragment {


    private AllAdapter adapter;
    @Bind(R.id.all_toolbar)Toolbar toolbar;
    @Bind(R.id.all_swipe)
    SwipeRefreshLayout mSwipe;
    @Bind(R.id.all_recycler)
    RecyclerView mRecycler;
    @Bind(R.id.dropMenu)
    DropDownMenu mMenu;
    private String[] headers = new String[]{"全部分类", "最新"};
    private String[] header_ten = new String[] {"十元专区","最新"};

    private String[] type = new String[]{"全部分类","手机数码","美妆护肤","母婴家居","珠宝饰品手表","箱包手袋","运动生活","十元专区","欧洲杯","美食天地","其他商品"};
    private String[] px = new String[]{"最新","人气","热度","价值（由高到低）","价值（由低到高）"};

    private List<Map<String, String>> listShop = new ArrayList<>();
    private List<Map<String,String>> listPrice = new ArrayList<>();
    String menu1,menu2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.all_fragment_layout,container,false);
        ButterKnife.bind(this,rootView);
        setToolabr();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDropMenu();
        initView();
    }

    private  void initView(){

        for (int i = 0;i<15;i++){
            Map<String,String> mapShop = new HashMap<>();
            mapShop.put("shop","商品"+i);
            listShop.add(mapShop);
        }

        for (int i =0;i<15;i++){
            Map<String,String> mapPrice = new HashMap<>();
            mapPrice.put("price","商品价值"+i+"00");
            listPrice.add(mapPrice);
        }
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(),"下拉刷新",Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(getActivity(),"加载更多",Toast.LENGTH_SHORT).show();
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
        adapter = new AllAdapter(getActivity(),listShop,listPrice);
        mRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new AllAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion) {
                startActivity(new Intent(getActivity(),ShopDetialActivity.class));
            }
        });
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
                    menu1 = type[RowIndex];
                    Toast.makeText(getActivity(),menu1,Toast.LENGTH_SHORT).show();

                } else if (ColumnIndex == 1) {
                    menu2 = px[RowIndex];
                    Toast.makeText(getActivity(),menu2,Toast.LENGTH_SHORT).show();

                }
//                initData();
            }
        });
        List<String[]> menuItems = new ArrayList<String[]>();
        menuItems.add(type);
        menuItems.add(px);

        mMenu.setmMenuItems(menuItems);
    }
    private void setToolabr(){
        toolbar.setTitle("");
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
    }
}
