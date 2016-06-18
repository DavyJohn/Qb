package com.six.qiangbao.fragments.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;

import butterknife.Bind;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class AddAddressActivity extends BaseActivity {

    @Bind(R.id.address_et)
    EditText mTaddress;
    @Bind(R.id.phone_et)
    EditText mTPhone;
    @Bind(R.id.name_et)
    EditText mTname;
    @Bind(R.id.sumbit_btn)
    Button mBsumbit;

    @Bind(R.id.add_address_bar)
    Toolbar mToolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address_main_layout);
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
}
