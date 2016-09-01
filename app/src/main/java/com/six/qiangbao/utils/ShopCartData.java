package com.six.qiangbao.utils;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Index;


/**
 * Created by 志浩 on 2016/8/25.
 */
public class ShopCartData  extends RealmObject {
    @Index
    private String id;
    private String name;
    private String image;
    private String gonum;
    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGonum() {
        return gonum;
    }

    public void setGonum(String gonum) {
        this.gonum = gonum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
