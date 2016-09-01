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
import com.six.qiangbao.fragments.mine.fragment.AllMineFragment;
import com.six.qiangbao.fragments.mine.fragment.EdFragment;
import com.six.qiangbao.fragments.mine.fragment.IngFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class JuActivity extends BaseActivity {

    private List<Fragment> listviw  = new ArrayList<>();
    private FragmentPagerAdapter fragmentPagerAdapter;

    @BindView(R.id.ju_bar)
    Toolbar mToolbar;

    @BindView(R.id.ju_page)
    ViewPager mViewPager;

    @BindView(R.id.all)
    LinearLayout mAll;
    @BindView(R.id.text_all)
    TextView mTextAll;

    @OnClick(R.id.all)
    void all() {
        mViewPager.setCurrentItem(0);
        com.saint.netlibrary.utils.ConstantUtil.STATUS = -1;
        setTab(0);
    }

    @BindView(R.id.ing)
    LinearLayout mIng;
    @BindView(R.id.text_ing)
    TextView mTextIng;

    @OnClick(R.id.ing)
    void ing() {
        com.saint.netlibrary.utils.ConstantUtil.STATUS = 1;
        mViewPager.setCurrentItem(1);
        setTab(1);
    }

    @BindView(R.id.ed)
    LinearLayout mEd;
    @BindView(R.id.text_ed)
    TextView mTextEd;

    @OnClick(R.id.ed)
    void ed() {
        com.saint.netlibrary.utils.ConstantUtil.STATUS = 0;
        mViewPager.setCurrentItem(2);
        setTab(2);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ju_main_layout);
        setUpToolBar();
        initdata();
    }
    private void  initdata(){
        Fragment all = new AllMineFragment();
        Fragment ing = new IngFragment();
        Fragment ed = new EdFragment();
        listviw.add(all);
        listviw.add(ing);
        listviw.add(ed);

        fragmentPagerAdapter  = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listviw.get(position);
            }

            @Override
            public int getCount() {
                return listviw.size();
            }
        };
        mViewPager.setAdapter(fragmentPagerAdapter);
        setTab(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int num  = mViewPager.getCurrentItem();
                setTab(num);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setUpToolBar() {
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

    private void setTab(int index) {
        clean();
        switch (index) {
            case 0:
                mAll.setBackgroundResource(R.drawable.lin1_shape);
                mTextAll.setTextColor(Color.parseColor("#ffffff"));
                break;
            case 1:
                mIng.setBackgroundResource(R.drawable.lin2_shape);
                mTextIng.setTextColor(Color.parseColor("#ffffff"));
                break;
            case 2:
                mEd.setBackgroundResource(R.drawable.lin3_shape);
                mTextEd.setTextColor(Color.parseColor("#ffffff"));
                break;
        }
    }

    private void clean() {
        mTextAll.setTextColor(Color.parseColor("#F16079"));
        mTextIng.setTextColor(Color.parseColor("#F16079"));
        mTextEd.setTextColor(Color.parseColor("#F16079"));
        mAll.setBackgroundResource(R.drawable.lin1_shape_normal);
        mIng.setBackgroundResource(R.drawable.lin2_shape_normal);
        mEd.setBackgroundResource(R.drawable.lin3_shape_normal);
    }
}
