package com.saint.netlibrary;

import com.saint.netlibrary.model.Login;
import com.saint.netlibrary.model.mine;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    /**
     * user
     * */
    @GET("/x/u")
    Observable<Response<mine>> mine ();
    /**
     * 登录
     * */
    @GET("/mobile/ajax/userlogin/{username}/{password}")
    Observable<Response<Login>> login(@Path("username") String username, @Path("password") String password);


}
