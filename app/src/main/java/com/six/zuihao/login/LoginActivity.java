package com.six.zuihao.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.six.zuihao.BaseActivity;
import com.six.zuihao.R;
import java.util.HashMap;

import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by yyx on 16/5/21.
 */
public class LoginActivity extends BaseActivity implements PlatformActionListener{

    Platform weChat,qq,wb;

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
}
