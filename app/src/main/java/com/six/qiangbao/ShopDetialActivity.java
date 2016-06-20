package com.six.qiangbao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.six.qiangbao.fragments.all.activity.AllRecordActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/6/17.
 */
public class ShopDetialActivity extends BaseActivity {

    @Bind(R.id.shop_detail_bar)
    Toolbar mToolBar;

    @OnClick(R.id.qiagbao_jilu) void ju(){
        startActivity(AllRecordActivity.class);
        finish();
    }

    @OnClick(R.id.tuwen_detail) void  tw(){
        Toast.makeText(this,"图文详情",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.qiangbao_history) void his(){
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_detial_main_layout);
        setUpToolBar();
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
}
