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
import com.six.qiangbao.fragments.mine.fragment.HboneFragment;
import com.six.qiangbao.fragments.mine.fragment.HbthreeFragment;
import com.six.qiangbao.fragments.mine.fragment.HbtwoFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class HbActivity extends BaseActivity {

    private List<Fragment> list = new ArrayList<>();
    private FragmentPagerAdapter fragmentPagerAdapter;
    @BindView(R.id.hb_pager)
    ViewPager mPager;
    @BindView(R.id.hb_bar)
    Toolbar mToolBar;
    @BindView(R.id.use)
    LinearLayout mLin1;
    @BindView(R.id.use_text)
    TextView mText1;
    @BindView(R.id.useed)
    LinearLayout mLin2;
    @BindView(R.id.useed_text)
    TextView mText2;
    @BindView(R.id.time_out)
    LinearLayout mLin3;
    @BindView(R.id.time_out_text)
    TextView mText3;
    @OnClick(R.id.use) void  use(){
        mPager.setCurrentItem(0);
        setTab(0);
    }
    @OnClick(R.id.useed) void  useed(){
        mPager.setCurrentItem(1);
        setTab(1);
    }
    @OnClick(R.id.time_out) void timeout(){
        mPager.setCurrentItem(2);
        setTab(2);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hb_main_layout);
        initData();
        setUpToolbar();
    }

    private void setUpToolbar(){
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
    private  void initData(){
        Fragment lin1 = new HboneFragment();
        Fragment lin2 =new HbtwoFragment();
        Fragment lin3 =new HbthreeFragment();
        list.add(lin1);
        list.add(lin2);
        list.add(lin3);
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
                int num  = mPager.getCurrentItem();
                setTab(num);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setTab(int index){
       clean();
        switch (index){
            case 0:
                mText1.setTextColor(Color.parseColor("#F16079"));
                mLin1.setBackgroundResource(R.drawable.hb_lin1_shape);
                break;
            case 1:
                mText2.setTextColor(Color.parseColor("#F16079"));
                mLin2.setBackgroundResource(R.drawable.hb_lin2_shape);
                break;
            case 2:
                mText3.setTextColor(Color.parseColor("#F16079"));
                mLin3.setBackgroundResource(R.drawable.hb_lin3_shape);
                break;
        }
    }
    private void  clean(){
        mLin1.setBackgroundResource(R.drawable.hb_lin1_shape_normal);
        mLin2.setBackgroundResource(R.drawable.hb_lin2_shape_normal);
        mLin3.setBackgroundResource(R.drawable.hb_lin3_shape_normal);
        mText1.setTextColor(Color.parseColor("#ffffff"));
        mText2.setTextColor(Color.parseColor("#ffffff"));
        mText3.setTextColor(Color.parseColor("#ffffff"));
    }
}
