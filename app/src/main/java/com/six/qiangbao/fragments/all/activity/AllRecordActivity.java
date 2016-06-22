package com.six.qiangbao.fragments.all.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.all.adapter.AllRecordAdapter;
import com.six.qiangbao.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 所有抢宝记录
 * Created by 志浩 on 2016/6/20.
 */
public class AllRecordActivity extends BaseActivity {

    @Bind(R.id.all_record_recycler)
    RecyclerView mRecycler;

    @Bind(R.id.all_record_bar)
    Toolbar mToolBar;

    private List<Map<String, String>> list = new ArrayList<>();
    private AllRecordAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_record_main_layout);
        setupToolbar();
        initData();
    }

    private void initData() {
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerDecoration(this));
        for (int i = 0; i <20; i++){
            Map<String,String> map = new HashMap<>();
            map.put("jilu","抢宝记录"+i);
            list.add(map);
        }
        adapter = new AllRecordAdapter(this);
        adapter.addData(list);
        mRecycler.setAdapter(adapter);


    }

    private void setupToolbar() {
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
