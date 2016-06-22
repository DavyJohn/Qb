package com.saint.netlibrary;

import com.saint.netlibrary.model.CarList;
import com.saint.netlibrary.model.LatestAnnouncement;
import com.saint.netlibrary.model.Login;
import com.saint.netlibrary.model.ShopDetails;
import com.saint.netlibrary.model.ShopListData;
import com.saint.netlibrary.model.mine;

import java.util.List;

import rx.Observable;

/**
 * Created by yyx on 16/3/1.
 */
public class ApiWrapper extends BangHttpClient {

//    public Observable<WxAuth> getWxToken(String url){
//        return getService().getWxToken(url)
//                .compose(this.<WxAuth>applySchedulers());
//    }

    /**
     * 获取user
     */
    public Observable<mine> getuser() {
        return getService().mine().compose(this.<mine>applySchedulers());
    }

    /**
     * login
     */

    public Observable<Login> login(String username, String password) {
        return getService().login(username, password).compose(this.<Login>applySchedulers());
    }

    /**
     * 最新揭晓
     */

    public Observable<LatestAnnouncement> announcement(String start, String number) {
        return getService().announcement(start, number).compose(this.<LatestAnnouncement>applySchedulers());
    }

    /**
     * 所有商品
     * */
    public Observable<List<ShopListData>> shopList (String cat ,String sel,String page){
        return getService().shoplist(cat ,sel ,page).compose(this.<List<ShopListData>>applySchedulers());
    }

    /**
     * 商品分类
     * */
    public Observable<List<CarList>> carList(){
        return getService().carList().compose(this.<List<CarList>>applySchedulers());
    }

    /**
     *商品详情
     * */
    public Observable<ShopDetails> shopDetails(String id){
        return getService().shopDetails(id).compose(this.<ShopDetails>applySchedulers());
    }

    public Observable<ShopDetails> shopUrl (String url){
        return getService().shopUrl(url).compose(this.<ShopDetails>applySchedulers());
    }

}
