package com.six.zuihao;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.saint.netlibrary.BangHttpClient;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by yyx on 16/5/20.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BangHttpClient.init(this);
        Logger.init().logLevel(LogLevel.FULL);
        ShareSDK.initSDK(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
