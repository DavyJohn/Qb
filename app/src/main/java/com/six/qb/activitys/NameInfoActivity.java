package com.six.qb.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.ChangeNameResultInfo;
import com.six.qb.BaseActivity;
import com.six.qb.R;
import com.six.qb.utils.AppManager;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/8/23.
 * 修改用户信息
 */
public class NameInfoActivity extends BaseActivity {

    @BindView(R.id.name_info_bar)
    Toolbar mToolbar;
    @BindView(R.id.name_et)
    EditText mText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_info_layout);
        AppManager.getAppManager().addActivity(mContext);
        initbar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.invitation_add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.invitation_add){
            postname(mText.getText().toString());
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initbar(){
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

    private void postname(String name){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.changnameresult(name,String.valueOf(1))
                .subscribe(newSubscriber(new Action1<ChangeNameResultInfo>() {
                    @Override
                    public void call(ChangeNameResultInfo changeNameResultInfo) {
                        System.out.print(changeNameResultInfo);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
