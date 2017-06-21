package com.six.qb.utils;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by 志浩 on 2016/8/22.
 */
public class AsyncHttpCilentUtil {
    private static AsyncHttpClient client = null;

    public synchronized static AsyncHttpClient getInstence(){
        if(client ==null){
            client = new AsyncHttpClient();
            client.setTimeout(1000*10);
        }
        return client;
    }
}
