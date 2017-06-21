package com.six.qb.login;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.MobileCheck;
import com.saint.netlibrary.utils.ConstantUtil;
import com.six.qb.BaseActivity;
import com.six.qb.R;
import com.six.qb.sms.ReadSmsContent;
import com.six.qb.utils.AppManager;


import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/23.
 */
public class RegisterCodeActivity extends BaseActivity {

    @BindView(R.id.register_code_bar)
    Toolbar mToolBar;
    private  CountDownTimer timer;
    private AppCompatActivity activity;
    @BindView(R.id.code_text)
    Button mBtnCode;
    @BindView(R.id.code_num)
    EditText mPhoneCode;
    @OnClick(R.id.code_text) void reCode(){
        re(ConstantUtil.CHECK_CODE);
    }

    @OnClick(R.id.register_code_next_btn) void yanzhen(){
        yanzhenma();
        startActivity(LoginActivity.class);
    }




    private ReadSmsContent readSmsContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_code_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        setUpToolbar();
        getCode(ConstantUtil.CHECK_CODE);
//        timeCountDown();
//
    }

    private void setUpToolbar(){
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


    private void getCode(String check_code){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.getCode(check_code)
                .subscribe(newSubscriber(new Action1<MobileCheck>() {
                    @Override
                    public void call(MobileCheck mobileCheck) {
                        readSmsContent = new ReadSmsContent(new Handler(), RegisterCodeActivity.this, mPhoneCode);
                        RegisterCodeActivity.this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, readSmsContent);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    private void timeCountDown() {
        timer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!activity.isFinishing()) {
                    mBtnCode.setText(millisUntilFinished / 1000 + "秒后可重发");
                } else {
                    timer.cancel();
                }
            }

            @Override
            public void onFinish() {
                mBtnCode.setEnabled(true);
                mBtnCode.setText("获取验证码");
            }
        }.start();

    }
    /**
     * 重发验证码
     * */
    private void re(String check_code){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.reCode(check_code)
                .subscribe(newSubscriber(new Action1<MobileCheck>() {
                    @Override
                    public void call(MobileCheck mobileCheck) {

                    }
                }));
        mCompositeSubscription.add(subscription);
    }
    private void yanzhenma() {
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.CheckMobileCode("1",ConstantUtil.CHECK_CODE,mPhoneCode.getText().toString())
                .subscribe(newSubscriber(new Action1<MobileCheck>() {
                    @Override
                    public void call(MobileCheck mobileCheck) {
                        System.out.print(mobileCheck.message);
                       Log.e("asdasdasdas",mobileCheck.message);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
}
