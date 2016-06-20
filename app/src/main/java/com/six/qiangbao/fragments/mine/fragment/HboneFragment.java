package com.six.qiangbao.fragments.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.six.qiangbao.BaseFragment;
import com.six.qiangbao.R;

import butterknife.Bind;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class HboneFragment extends BaseFragment {
    @Bind(R.id.address_lin)
    LinearLayout mLin;
    @Bind(R.id.hb_one_recycler)
    RecyclerView mRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hb_one_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
