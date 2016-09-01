package com.six.qiangbao;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.saint.netlibrary.BangHttpClient;

import cn.beecloud.BeeCloud;
import cn.sharesdk.framework.ShareSDK;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yyx on 16/5/20.
 */
public class BaseApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        BangHttpClient.init(this);
        Logger.init().logLevel(LogLevel.FULL);
        ShareSDK.initSDK(this);
        BeeCloud.setSandbox(false);
        BeeCloud.setAppIdAndSecret("471b3b98-72c6-48b7-bde0-abe764908923",
                "6f7db685-2f0c-40ea-910b-66fad7f0e8e5");

        BaseApplication.context = getApplicationContext();

        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("data.realm")
                .schemaVersion(2)//版本号
                .inMemory()//设置放在缓存中
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static Context getAppContext() {
        return BaseApplication.context;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
