package com.six.qiangbao.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class RegisterActivity extends BaseActivity {
    @Bind(R.id.register_bar)
    Toolbar mToolbar;
    @Bind(R.id.register_phone)
    EditText mTphone;
    @Bind(R.id.register_pass)
    EditText mTpass;
    @Bind(R.id.register_pass_ag)
    EditText mTpassAg;
    @Bind(R.id.next_btn)
    Button btn;
    @Bind(R.id.check_box)
    CheckBox box;
    @OnClick(R.id.xieyi) void  xieyi(){
        Toast.makeText(this,"查看协议",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.next_btn) void next(){
        Toast.makeText(this,"new",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main_layout);
        setUpToolbar();
        initdata();

    }

    private void initdata(){
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    btn.setBackgroundResource(R.drawable.ti_button_shape);
                    btn.setClickable(true);
                }else {
                    btn.setBackgroundResource(R.drawable.ti_button_shap_un);
                    btn.setClickable(false);
                }
            }
        });
    }

    private void setUpToolbar(){
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
