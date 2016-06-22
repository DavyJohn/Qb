package com.six.qiangbao.fragments.newst;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.LatestAnnouncement;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.utils.ConstantUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.Bind;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/6/21.
 */
public class ResultsRevealedActivity extends BaseActivity {

    @Bind(R.id.results_bar)
    Toolbar mToolBar;
    @Bind(R.id.result_shop_image)
    ImageView mImageShop;
    @Bind(R.id.results_user_image)
    ImageView mImageUser;
    @Bind(R.id.results_username)
    TextView mTextUser;
    @Bind(R.id.results_user_address)
    TextView mTextUserAddress;
    @Bind(R.id.results_time)
    TextView mTextResultTime;
    @Bind(R.id.results_qb_time)
    TextView mTextQBTime;
    @Bind(R.id.qiangbao_renshu)
    TextView mTextRenshu;
    @Bind(R.id.qishu)
    TextView mTextQs;
    @Bind(R.id.qb_code)
    TextView  mTextCode;
    @Bind(R.id.btn_results)
    Button mBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_revealed_main_layout);
        setUpToolBar();

        Picasso.with(this).load(ConstantUtil.IMAGE_HEAD+getIntent().getStringExtra("shop_image")).into(mImageShop);
        Picasso.with(this).load(ConstantUtil.IMAGE_HEAD+getIntent().getStringExtra("user_image")).into(mImageUser);
        mTextUser.setText(getIntent().getStringExtra("username"));
        mTextUserAddress.setText(getIntent().getStringExtra("user_address"));
        mTextQs.setText("(第"+getIntent().getStringExtra("qishu")+")"+"幸运抢宝码：");
        String  mNext = getIntent().getStringExtra("qishu")+1;
        mTextCode.setText(getIntent().getStringExtra("user_code"));
        mTextRenshu.setText("商品获得者本期共抢宝"+getIntent().getStringExtra("renshu")+"人次");
        mTextResultTime.setText("揭晓时间"+getIntent().getStringExtra("jiexiao"));
        mBtn.setText("第"+mNext+"期正在进行...");
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
