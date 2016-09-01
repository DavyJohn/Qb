package com.saint.netlibrary;

import com.saint.netlibrary.model.AddAddressSuccess;
import com.saint.netlibrary.model.AddShopCarResult;
import com.saint.netlibrary.model.AddressDataItem;
import com.saint.netlibrary.model.Avatar;
import com.saint.netlibrary.model.BannerData;
import com.saint.netlibrary.model.CarList;

import com.saint.netlibrary.model.ChangeNameResultInfo;
import com.saint.netlibrary.model.DelCartItemResult;
import com.saint.netlibrary.model.GoodsQbJuData;
import com.saint.netlibrary.model.HongbaoData;
import com.saint.netlibrary.model.LatestAnnouncement;
import com.saint.netlibrary.model.Login;
import com.saint.netlibrary.model.Mine;
import com.saint.netlibrary.model.MobileCheck;
import com.saint.netlibrary.model.MyQbJu;
import com.saint.netlibrary.model.PayForResultData;
import com.saint.netlibrary.model.RechargeData;
import com.saint.netlibrary.model.ShopDetails;
import com.saint.netlibrary.model.ShopListData;
import com.saint.netlibrary.model.WqGoods;
import com.saint.netlibrary.model.XIaoFeiJilu;


import java.io.File;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
     * 我
     * */
    @GET("/x/u")
    Observable<Response<Mine>> me();

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
     * 注册*/
    @FormUrlEncoded
    @POST("/x/u/register")
    Observable<Response<String>> getCode(@Field("submit") String submit , @Field("name")String name
            , @Field("userpassword") String userpassword, @Field("userpassword2") String userpassword2);


    /**
     * 验证验证码*/
    @FormUrlEncoded
    @POST("/x/u/mobilecheck")
    Observable<Response<MobileCheck>> Check_code(@Field("submit") String submit,@Field("check_code") String checkCode,@Field("checkcodes") String mobileCode);

    /**
     * 验证验证码
     * */
    @FormUrlEncoded
    @POST("/x/u/mobilecheck")
    Observable<Response<MobileCheck>> getCheck(@Field("check_code")String Check_code);

    /***
     * 重发验证码
     */

    @FormUrlEncoded
    @POST("/x/u/sendmobile")
    Observable<Response<MobileCheck>> reCode(@Field("check_code") String Check_code);

    /**
     * 首页幻灯片
     * */

    @GET("/mobile/ajax/slides")
    Observable<Response<BannerData>> bannerdata();

    /**
     *商品详情
     * */
    @GET("/x/g/item/{id}")
    Observable<Response<ShopDetails>> shopDetails(@Path("id") String id);

    @GET
    Observable<Response<WqGoods>> shopUrl(@Url String url);

    /**
     *往期商品详情
     * */

    @GET("/x/g/dataserver/{id}")
    Observable<Response<WqGoods>> wqgoods(@Path("id") String id);

    /**
     * 我的抢宝记录
     * */

    @GET("/mobile/shopajax/getUserBuyList/{start}/{per_page}/{is_count}/{status}")
    Observable<Response<MyQbJu>> ju(@Path("start") String start,
                                    @Path("per_page") String per_page,
                                    @Path("is_count")String is_count,
                                    @Path("status") String status);
    /**
     * 已获得的商品
     * */
    @GET("/mobile/shopajax/getUserOrderList/{start}/{per_page}")
    Observable<Response<MyQbJu>> owned(@Path("start")String start,
                                       @Path("per_page")String per_page);

    /**
     * 列出所有收货地址
     * */
    @GET("/x/u/address")
    Observable<Response<List<AddressDataItem>>> alladdress();

    /**
     * 新增收货地址
     * */

    @FormUrlEncoded
    @POST("/x/u/useraddress")
    Observable<Response<AddAddressSuccess>> postaddress(@Field("sheng") String sheng,
                                                        @Field("shi") String shi,
                                                        @Field("xian") String xian,
                                                        @Field("jiedao") String jiedao,
                                                        @Field("youbian")String youbian,
                                                        @Field("shouhuoren")String shouhuoren,
                                                        @Field("mobile") String mobile,
                                                        @Field("shifoufahuo")String shifoufahuo,
                                                        @Field("submit") String submit);

    /**
     * 加入购物车
     * */

    @GET("/mobile/ajax/addShopCart/{id}/{num}/{from}")
    Observable<Response<AddShopCarResult>> addcar(@Path("id")String id,
                                                  @Path("num")String num);

    @GET("/mobile/ajax/addShopCart/{id}/{num}/{from}")
    Observable<Response<AddShopCarResult>> addcartnum(@Path("id")String id,
                                                  @Path("num")String num,
                                                      @Path("from")String from);

    /**
     *删除购物车商品
     * */
    @GET("/mobile/ajax/delCartItem/{id}")
    Observable<Response<DelCartItemResult>> delcart(@Path("id")String id);

    /**
     * 邀请码
     * */
    @FormUrlEncoded
    @POST("/x/u/userinvation")
    Observable<Response<String>> invitation(@Field("yaoqingren")String yaoqingren);

    /**
     * 红包
     * */

    @GET("/x/u/hongbao")
    Observable<Response<List<HongbaoData>>> hongbao(@Query("order_money")String order_money,
                                                    @Query("used") String used,
                                                    @Query("valid")String valid,
                                                    @Query("invalid")String invalid);

    /**
     *余额充值
     * */

    @FormUrlEncoded
    @POST("/member/cart/beeaddmoney")
    Observable<Response<RechargeData>> pay(@Field("amount") Double amount);

    /**
     * 修改用户名和签名
     * */
    @FormUrlEncoded
    @POST("/x/u/modify_name")
    Observable<Response<ChangeNameResultInfo>> changnameresult(@Field("username")String username,
                                                               @Field("submit") String submit);



    /**
     * 默认
     * */
    @GET("/x/u/morenaddress/{id}")
    Observable<Response<Avatar>> moren(@Path("id") String id);

    /**
     * 删除地址
     * */

    @GET("/x/u/deladdress/{id}")
    Observable<Response<Avatar>> del(@Path("id")String id);
    /**
     * 获取消费记录
     * */
    @GET("/mobile/shopajax/getUserConsumption/{start}/{per_page}")
    Observable<Response<XIaoFeiJilu>> xiaofei(@Path("start")String start,
                                              @Path("per_page") String per_page);


    /***
     * 所有抢宝记录
     */

    @GET("/x/g/buyrecords/{id}")
    Observable<Response<List<GoodsQbJuData>>> qbdata(@Path("id") String id);

    /**
     * 开始支付
     * */
    @GET("/x/u/pay")
    Observable<Response<PayForResultData>> pay();

    /**
     * 实际支付
     * */

    @GET("/x/u/paysubmit/{method}/{bank}/{money}/{fufen}/{code}/{hongbao}")
    Observable<Response<DelCartItemResult>> payfor(@Path("method") String method,
                                                   @Path("bank") String bank,
                                                   @Path("money")String money,
                                                   @Path("fufen")String fufen,
                                                   @Path("code") String code,
                                                   @Path("hongbao")String hongbao);

}

