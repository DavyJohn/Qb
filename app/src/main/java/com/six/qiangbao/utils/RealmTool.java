package com.six.qiangbao.utils;

import android.util.Log;

import com.saint.netlibrary.model.ShopDetails;
import com.saint.netlibrary.model.ShopDetailsItem;
import com.saint.netlibrary.model.ShopListData;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by 志浩 on 2016/8/25.
 */
public class RealmTool {
    private static RealmTool instance = null;
    private Realm realm;
    public synchronized static RealmTool getInstance() {
        if (instance == null) {
            instance = new RealmTool();
            instance.realm = Realm.getDefaultInstance();
        }
         return instance;
    }

    public  void realmdata(ShopListData item, String num){
        instance.realm.beginTransaction();
        //添加数据
        RealmResults<ShopCartData> results = instance.realm.where(ShopCartData.class).equalTo("id",item.id).findAll();
        if (results.size() != 0 ){
            results.get(0).setGonum(String.valueOf(Integer.parseInt(results.get(0).getGonum())+1));
        }else {
            ShopCartData data = instance.realm.createObject(ShopCartData.class);
            data.setName(item.title);
            data.setImage(item.thumb);
            data.setGonum(num);
            data.setId(item.id);
            data.setMoney(item.yunjiage);
        }
        instance.realm.commitTransaction();


        RealmResults<ShopCartData> realmResults = instance.realm.where(ShopCartData.class).findAll();
        for (ShopCartData d :realmResults){
            Log.e("=================realm",d.getId()+"===="+d.getName()+"===="+d.getGonum());
        }
    }

    public  void realmdata(ShopDetailsItem item, String num){
        instance.realm.beginTransaction();
        //添加数据
        RealmResults<ShopCartData> results = instance.realm.where(ShopCartData.class).equalTo("id",item.id).findAll();
        if (results.size() != 0 ){
            results.get(0).setGonum(String.valueOf(Integer.parseInt(results.get(0).getGonum())+1));
        }else {
            ShopCartData data = instance.realm.createObject(ShopCartData.class);
            data.setName(item.title);
            data.setImage(item.thumb);
            data.setGonum(num);
            data.setId(item.id);
            data.setMoney(item.yunjiage);
        }
        instance.realm.commitTransaction();


        RealmResults<ShopCartData> realmResults = instance.realm.where(ShopCartData.class).findAll();
        for (ShopCartData d :realmResults){
            Log.e("=================realm",d.getId()+"===="+d.getName()+"===="+d.getGonum());
        }
    }
}
