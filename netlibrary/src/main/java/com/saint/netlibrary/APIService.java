package com.saint.netlibrary;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by yyx on 16/3/1.
 */
public interface APIService {
    /**
     * 获取微信Token
     * @param url
     * @return

    @GET
    Observable<Response<WxAuth>> getWxToken(@Url String url);*/
}
