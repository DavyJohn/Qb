package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/23.
 * 获取消费记录
 */
public class ItmesData {

    private String uid;
    private String type;
    private String pay;
    private String content;
    private String money;
    private String time;

    public ItmesData(String uid, String type, String pay, String content, String money, String time) {
        this.uid = uid;
        this.type = type;
        this.pay = pay;
        this.content = content;
        this.money = money;
        this.time = time;
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

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ItmesData{" +
                "uid='" + uid + '\'' +
                ", type='" + type + '\'' +
                ", pay='" + pay + '\'' +
                ", content='" + content + '\'' +
                ", money='" + money + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
