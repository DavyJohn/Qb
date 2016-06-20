package com.saint.netlibrary;

import com.saint.netlibrary.model.LatestAnnouncement;
import com.saint.netlibrary.model.Login;
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
     * */
    public Observable<mine> getuser(){
        return getService().mine().compose(this.<mine>applySchedulers());
    }

    /**
     * login
     * */

    public Observable<Login> login(String username,String password){
        return getService().login(username,password).compose(this.<Login>applySchedulers());
    }

    /**
     * 最新揭晓
     * */

    public Observable<LatestAnnouncement> announcement(String start ,String number){
        return getService().announcement(start,number).compose(this.<LatestAnnouncement>applySchedulers());
    }
}
