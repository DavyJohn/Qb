package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/24.
 */
public class HongbaoData {

    private String id;
    private String money;
    private String limit;
    private String uid;
    private String type;
    private String created_at;
    private String expire_at;
    private String used_at;

    public HongbaoData(String id, String money, String limit, String uid, String type, String created_at, String expire_at, String used_at) {
        this.id = id;
        this.money = money;
        this.limit = limit;
        this.uid = uid;
        this.type = type;
        this.created_at = created_at;
        this.expire_at = expire_at;
        this.used_at = used_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getExpire_at() {
        return expire_at;
    }

    public void setExpire_at(String expire_at) {
        this.expire_at = expire_at;
    }

    public String getUsed_at() {
        return used_at;
    }

    public void setUsed_at(String used_at) {
        this.used_at = used_at;
    }

    @Override
    public String toString() {
        return "HongbaoData{" +
                "id='" + id + '\'' +
                ", money='" + money + '\'' +
                ", limit='" + limit + '\'' +
                ", uid='" + uid + '\'' +
                ", type='" + type + '\'' +
                ", created_at='" + created_at + '\'' +
                ", expire_at='" + expire_at + '\'' +
                ", used_at='" + used_at + '\'' +
                '}';
    }
}
