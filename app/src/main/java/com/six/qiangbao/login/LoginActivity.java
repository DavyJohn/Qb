package com.six.qiangbao.login;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.orhanobut.logger.Logger;
import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.Login;

import com.saint.netlibrary.model.Mine;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.utils.AppManager;
import com.six.qiangbao.utils.AsyncHttpCilentUtil;
import com.six.qiangbao.utils.ConstantUtil;

import org.apache.http.client.protocol.ClientContext;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.impl.client.AbstractHttpClient;
import cz.msebera.android.httpclient.protocol.HttpContext;
import retrofit2.Response;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by yyx on 16/5/21.
 */
public class LoginActivity extends BaseActivity implements PlatformActionListener{

    Platform weChat,qq,wb;


    @BindView(R.id.login_bar)
    Toolbar mToolbar;
    @BindView(R.id.phone)
    EditText mEphone;
    @BindView(R.id.pass)
    EditText mEpass;
    @BindView(R.id.btn_login)
    Button mBlogin;

    @OnClick(R.id.register) void register(){
        Intent intent = new Intent(this,RegisterCodeActivity.class);//获取验证码
        startActivity(intent);
    }

    @OnClick(R.id.btn_login) void  login(){
        final ApiWrapper wrapper = new ApiWrapper();
        final String phone = mEphone.getText().toString();
        final String pass = mEpass.getText().toString();
        Subscription subscription = wrapper.login(phone,pass)
                .subscribe(newSubscriber(new Action1<Login>() {
                    @Override
                    public void call(Login login) {
                        int  text =login.state;
                        if (text == 0){
                            ConstantUtil.isMineChange = 1;
                            finish();
                        }else {
                            //登陆失败
                            ConstantUtil.isMineChange = 0;
                        }

                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @OnClick(R.id.loginBtn) void weChatLogin(){
        weChat = ShareSDK.getPlatform(Wechat.NAME);
        weChat.setPlatformActionListener(this);
        weChat.authorize();
    }

    @OnClick(R.id.qqBtn) void qqLogin(){
        qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(this);
        qq.authorize();
    }

    @OnClick(R.id.weiboBtn) void wbLogin(){
        wb = ShareSDK.getPlatform(SinaWeibo.NAME);
        wb.setPlatformActionListener(this);
        wb.SSOSetting(false);
        wb.authorize();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppManager.getAppManager().addActivity(mContext);
        setUpToolBar();
    }

    private void setUpToolBar(){
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.left_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             AppManager.getAppManager().AppExit(mContext);
                System.exit(0);//有问题带纠正

            }
        });
    }




    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Logger.i("");
        if (i == Platform.ACTION_AUTHORIZING){
            PlatformDb platformDb = platform.getDb();
            if (!TextUtils.isEmpty(platformDb.getUserId())){
                if (platformDb.getPlatformNname().equals(Wechat.NAME)){

                }else if (platformDb.getPlatformNname().equals(QQ.NAME)){

                }else if (platformDb.getPlatformNname().equals(SinaWeibo.NAME)){

                }
            }
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Logger.e("授权出错,"+throwable.toString());
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Logger.i("用户取消了授权");
    }
    //判断是否为手机号码
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();

    }
}
