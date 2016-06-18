package com.six.qiangbao.fragments.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;

import butterknife.Bind;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class AddressActivity extends BaseActivity {

    @Bind(R.id.address_recycler)
    RecyclerView mRecycler;
    @Bind(R.id.address_bar)
    Toolbar mToolBAR;
    @Bind(R.id.address_lin)
    LinearLayout mLin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_main_layout);
        setUptoolBar();
    }

    private void setUptoolBar(){
        mToolBAR.setTitle("");
        setSupportActionBar(mToolBAR);
        mToolBAR.setNavigationIcon(R.drawable.left_icon);
        mToolBAR.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add){
            Intent intent = new Intent(this,AddAddressActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
