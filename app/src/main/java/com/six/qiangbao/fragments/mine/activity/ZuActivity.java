package com.six.qiangbao.fragments.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.mine.fragment.ZuRightFragment;
import com.six.qiangbao.fragments.mine.fragment.ZuleftFragment;
import com.six.qiangbao.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class ZuActivity extends BaseActivity {

    private List<Fragment> list = new ArrayList<>();
    private FragmentPagerAdapter fragmentPagerAdapter;


    @BindView(R.id.zu_bar)
    Toolbar mToolBar;

    @BindView(R.id.zu_lin1)
    LinearLayout mLin1;

    @BindView(R.id.zu_lin2)
    LinearLayout mLin2;

    @BindView(R.id.zu_text1)
    TextView mText1;
    @BindView(R.id.zu_text2)
    TextView mText2;
    @BindView(R.id.zu_pager)
    ViewPager mPager;

    @OnClick(R.id.zu_lin1) void lin1(){
        mPager.setCurrentItem(0);
        setTab(0);
    }

    @OnClick(R.id.zu_lin2) void  lin2(){
        mPager.setCurrentItem(1);
        setTab(1);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zu_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        setupToolBar();
        initdata();
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
        mPager.setAdapter(fragmentPagerAdapter);
        setTab(0);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int num = mPager.getCurrentItem();
                setTab(num);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void  initdata(){
        Fragment left = new ZuleftFragment();
        Fragment right = new ZuRightFragment();
        list.add(left);
        list.add(right);

    }

    private void setupToolBar(){
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

    private void setTab(int index){
        clean();
        switch (index){
            case 0:
                mLin1.setBackgroundResource(R.drawable.lin1_shape);
                mText1.setTextColor(Color.parseColor("#ffffff"));
                break;
            case 1:
                mLin2.setBackgroundResource(R.drawable.lin3_shape);
                mText2.setTextColor(Color.parseColor("#ffffff"));
                break;
        }
    }

    private void clean(){
        mLin1.setBackgroundResource(R.drawable.lin1_shape_normal);
        mLin2.setBackgroundResource(R.drawable.lin3_shape_normal);
        mText1.setTextColor(Color.parseColor("#F16079"));
        mText2.setTextColor(Color.parseColor("#F16079"));
    }
}
