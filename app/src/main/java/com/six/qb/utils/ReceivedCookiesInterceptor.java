package com.six.qb.utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by 志浩 on 2016/8/22.
 */
public class ReceivedCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        List<String> cookieList =  originalResponse.headers("Set-Cookie");
        if(cookieList != null) {
            for(String s:cookieList) {//Cookie的格式为:cookieName=cookieValue;path=xxx
                //保存你需要的cookie数据
            }
        }
        return originalResponse;
    }
}
