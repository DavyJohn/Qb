package com.six.qb.fragments.all.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.six.qb.BaseActivity;
import com.six.qb.R;
import com.six.qb.utils.AppManager;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/6/22.
 */
public class GraphicDetailsActivity extends BaseActivity {

    @BindView(R.id.text_image_details)
    WebView mWebView;
    @BindView(R.id.details_image_bar)
    Toolbar mToolbar;
    private String conten;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphic_details_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        setUpToolbar();
        initWebView();
    }

    private void initWebView(){
        WebSettings  settings =mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        mWebView.setHapticFeedbackEnabled(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        conten = getIntent().getStringExtra("content");
        Log.e("content",conten);
        String encoding = "UTF-8";
        String mimeType = "text/html";
        final String html =conten;
        mWebView.loadDataWithBaseURL("file://", html,mimeType, encoding, "about:blank");

    }

    private void  setUpToolbar(){
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
}
