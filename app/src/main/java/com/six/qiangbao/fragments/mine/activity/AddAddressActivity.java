package com.six.qiangbao.fragments.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.AddAddressSuccess;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class AddAddressActivity extends BaseActivity {

    @BindView(R.id.sheng_et)
    EditText mSheng;
    @BindView(R.id.shi_et)
    EditText mShi;
    @BindView(R.id.xian_et)
    EditText mXian;
    @BindView(R.id.jiedao_et)
    EditText mJeidao;
    @BindView(R.id.phone_et)
    EditText mTPhone;
    @BindView(R.id.name_et)
    EditText mTname;
    @BindView(R.id.sumbit_btn)
    Button mBsumbit;
    @BindView(R.id.youbian)
    EditText mYoub;
    @BindView(R.id.add_address_bar)
    Toolbar mToolBar;

    @OnClick(R.id.sumbit_btn) void send(){
        String sheng = mSheng.getText().toString();
        String shi = mShi.getText().toString();
        String xian = mXian.getText().toString();
        String jiedao = mJeidao.getText().toString();
        post(sheng,shi,xian,jiedao,mYoub.getText().toString(),mTname.getText().toString(),mTPhone.getText().toString(),String.valueOf(0),String.valueOf(1));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address_main_layout);
        setUpToolBar();

    }

    private void post(String sheng,String shi,String xian,String jiedao,String youbian,String shouhuoren,String mobile,String shifoufahuo,String submit){

        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.postaddress(sheng,shi,xian,jiedao,youbian,shouhuoren,mobile,String.valueOf(0),String.valueOf(1))
                .subscribe(newSubscriber(new Action1<AddAddressSuccess>() {
                    @Override
                    public void call(AddAddressSuccess addAddressSuccess) {
                        System.out.print(addAddressSuccess);
                    }
                }));
        mCompositeSubscription.add(subscription);

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
