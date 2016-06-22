package com.saint.netlibrary;

import com.saint.netlibrary.model.CarList;
import com.saint.netlibrary.model.LatestAnnouncement;
import com.saint.netlibrary.model.Login;
import com.saint.netlibrary.model.ShopDetails;
import com.saint.netlibrary.model.ShopListData;
import com.saint.netlibrary.model.mine;

import java.util.List;

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

    /**
     *zuixin揭晓
     * */
    @GET("/mobile/ajax/getLotteryList/{start}/{number}/0")
    Observable<Response<LatestAnnouncement>> announcement(@Path("start") String start,@Path("number") String number );

    /**
     * 所有商品
     * */

    @GET("/x/g/glist/{cat}/{sel}/{page}")
    Observable<Response<List<ShopListData>>> shoplist(@Path("cat") String cat ,@Path("sel") String sel
            ,@Path("page") String page);


    /**
     * 商品分类
     * */
    @GET("/x/g/catlist")
    Observable<Response<List<CarList>>> carList();

    /**
     *商品详情
     * */
    @GET("/x/g/item/{id}")
    Observable<Response<ShopDetails>> shopDetails(@Path("id") String id);
    @GET
    Observable<Response<ShopDetails>> shopUrl(@Url String url);
}
