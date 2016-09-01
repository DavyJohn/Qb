package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/24.
 */
public class GoodsQbJuData {

    private String id;
    private String code;
    private String code_tmp;
    private String username;
    private String uphoto;
    private String uid;
    private String shopid;
    private String shopqishu;
    private String gonumber;
    private String goucode;
    private String moneycount;
    private String huode;
    private String pay_type;
    private String ip;
    private String status;
    private String time;
    private String company;
    private String company_code;
    private String company_money;

    public GoodsQbJuData(String id, String code, String code_tmp, String username, String uphoto, String uid, String shopid, String shopqishu, String gonumber, String goucode, String moneycount, String huode, String pay_type, String ip, String status, String time, String company, String company_code, String company_money) {
        this.id = id;
        this.code = code;
        this.code_tmp = code_tmp;
        this.username = username;
        this.uphoto = uphoto;
        this.uid = uid;
        this.shopid = shopid;
        this.shopqishu = shopqishu;
        this.gonumber = gonumber;
        this.goucode = goucode;
        this.moneycount = moneycount;
        this.huode = huode;
        this.pay_type = pay_type;
        this.ip = ip;
        this.status = status;
        this.time = time;
        this.company = company;
        this.company_code = company_code;
        this.company_money = company_money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode_tmp() {
        return code_tmp;
    }

    public void setCode_tmp(String code_tmp) {
        this.code_tmp = code_tmp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUphoto() {
        return uphoto;
    }

    public void setUphoto(String uphoto) {
        this.uphoto = uphoto;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getShopqishu() {
        return shopqishu;
    }

    public void setShopqishu(String shopqishu) {
        this.shopqishu = shopqishu;
    }

    public String getGonumber() {
        return gonumber;
    }

    public void setGonumber(String gonumber) {
        this.gonumber = gonumber;
    }

    public String getGoucode() {
        return goucode;
    }

    public void setGoucode(String goucode) {
        this.goucode = goucode;
    }

    public String getMoneycount() {
        return moneycount;
    }

    public void setMoneycount(String moneycount) {
        this.moneycount = moneycount;
    }

    public String getHuode() {
        return huode;
    }

    public void setHuode(String huode) {
        this.huode = huode;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_money() {
        return company_money;
    }

    public void setCompany_money(String company_money) {
        this.company_money = company_money;
    }

    @Override
    public String toString() {
        return "GoodsQbJuData{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", code_tmp='" + code_tmp + '\'' +
                ", username='" + username + '\'' +
                ", uphoto='" + uphoto + '\'' +
                ", uid='" + uid + '\'' +
                ", shopid='" + shopid + '\'' +
                ", shopqishu='" + shopqishu + '\'' +
                ", gonumber='" + gonumber + '\'' +
                ", goucode='" + goucode + '\'' +
                ", moneycount='" + moneycount + '\'' +
                ", huode='" + huode + '\'' +
                ", pay_type='" + pay_type + '\'' +
                ", ip='" + ip + '\'' +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                ", company='" + company + '\'' +
                ", company_code='" + company_code + '\'' +
                ", company_money='" + company_money + '\'' +
                '}';
    }
}
