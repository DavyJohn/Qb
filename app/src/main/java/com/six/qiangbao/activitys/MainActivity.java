package com.six.qiangbao.activitys;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.cart.CartFragment;
import com.six.qiangbao.fragments.newst.NewFragment;
import com.six.qiangbao.fragments.all.AllFragment;
import com.six.qiangbao.fragments.main.MainFragment;
import com.six.qiangbao.fragments.mine.MineFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    private FragmentManager fm;
    private MainFragment mainFragment;
    private AllFragment allFragment;
    private NewFragment newFragment;
    private MineFragment mineFragment;
    private CartFragment cartFragment;
    private int DEFULT_FRAGMENT_INDEX = 0;

    @Bind(R.id.main_bottom_navigation) AHBottomNavigation bottomNavigation;
    private AHBottomNavigationItem item1;
    private AHBottomNavigationItem item2;
    private AHBottomNavigationItem item3;
    private AHBottomNavigationItem item4;
    private AHBottomNavigationItem item5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        fm = getSupportFragmentManager();
        initTabbar();
        showFragment(DEFULT_FRAGMENT_INDEX);
    }

    private void initTabbar(){
        item1 = new AHBottomNavigationItem(R.string.mian_tab_name,R.drawable.tab_main_icon,R.color.white);
        item2 = new AHBottomNavigationItem(R.string.all_tab_name,R.drawable.tab_all_icon,R.color.white);
        item3 = new AHBottomNavigationItem(R.string.new_tab_name,R.drawable.tab_new_icon,R.color.white);
        item4 = new AHBottomNavigationItem(R.string.cart_tab_name,R.drawable.tab_cart_icon,R.color.white);
        item5 = new AHBottomNavigationItem(R.string.mine_tab_name,R.drawable.tab_me_icon,R.color.white);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
        bottomNavigation.setAccentColor(Color.parseColor("#F16079"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setForceTint(false);
        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setColored(false);
        bottomNavigation.setCurrentItem(DEFULT_FRAGMENT_INDEX);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                if (!wasSelected){
                    showFragment(position);
                }
            }
        });
    }

    private void showFragment(int index){
        DEFULT_FRAGMENT_INDEX = index;
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (index){
            case 0:
                if (mainFragment == null){
                    mainFragment = new MainFragment();
                    ft.add(R.id.main_content,mainFragment);
                }else {
                    ft.show(mainFragment);
                }
                break;
            case 1:
                if (allFragment == null){
                    allFragment = new AllFragment();
                    ft.add(R.id.main_content,allFragment);
                }else {
                    ft.show(allFragment);
                }
                break;
            case 2:
                if (newFragment == null){
                    newFragment = new NewFragment();
                    ft.add(R.id.main_content,newFragment);
                }else {
                    ft.show(newFragment);
                }
                break;
            case 3:
                if (cartFragment == null){
                    cartFragment = new CartFragment();
                    ft.add(R.id.main_content,cartFragment);
                }else {
                    ft.show(cartFragment);
                }
                break;
            case 4:
                if (mineFragment == null){
                    mineFragment = new MineFragment();
                    ft.add(R.id.main_content,mineFragment);
                }else {
                    ft.show(mineFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction ft){
        if (mainFragment != null) ft.hide(mainFragment);
        if (allFragment != null) ft.hide(allFragment);
        if (newFragment != null) ft.hide(newFragment);
        if (mineFragment != null) ft.hide(mineFragment);
        if (cartFragment != null) ft.hide(cartFragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("postion",DEFULT_FRAGMENT_INDEX);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        DEFULT_FRAGMENT_INDEX = savedInstanceState.getInt("postion");
        showFragment(DEFULT_FRAGMENT_INDEX);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
