package com.six.qiangbao.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.six.qiangbao.BaseFragment;
import com.six.qiangbao.R;
import com.six.qiangbao.activitys.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yyx on 16/5/20.
 */
public class MainFragment extends BaseFragment {

    @Bind(R.id.main_toolbar) Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_layout,container,false);
        ButterKnife.bind(this,rootView);
        toolbar.setTitle("");
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        return rootView;
    }
}