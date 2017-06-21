package com.six.qiangbao.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.saint.netlibrary.ApiWrapper;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.utils.AppManager;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/8/22.
 */
public class InvitationActivity extends BaseActivity {

    @BindView(R.id.invitation_code)
    EditText mCode;

    @BindView(R.id.invitation_bar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(mContext);
        setContentView(R.layout.invitation_main_layout);
        setupbar();
    }

    private void setupbar(){
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


    private void invitation(String code){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.invitation(code)
                .subscribe(newSubscriber(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.print(s);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(InvitationActivity.this);
        inflater.inflate(R.menu.invitation_add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==R.id.invitation_add){
            invitation(mCode.getText().toString());
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
