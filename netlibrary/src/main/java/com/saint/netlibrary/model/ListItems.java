package com.saint.netlibrary.model;

import org.w3c.dom.ProcessingInstruction;

import java.io.StringReader;

/**
 * Created by 志浩 on 2016/8/19.
 */
public class ListItems {
    private String id;
    private String code;
    private String username;
    private String uphoto;
    private String uid;
    private String shopid;
    private String shopname;
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
    private String sid;
    private String cateid;
    private String brandid;
    private String title;
    private String title_style;
    private String title2;
    private String money;
    private String yunjiage;
    private String zongrenshu;
    private String canyurenshu;
    private String shenyurenshu;
    private String def_renshu;
    private String qishu;
    private String maxqishu;
    private String thumb;

    public ListItems(String id, String code, String username, String uphoto, String uid, String shopid, String shopname, String shopqishu, String gonumber, String goucode, String moneycount, String huode, String pay_type, String ip, String status, String time, String company, String company_code, String company_money, String sid, String cateid, String brandid, String title, String title_style, String title2, String money, String yunjiage, String zongrenshu, String canyurenshu, String shenyurenshu, String def_renshu, String qishu, String maxqishu, String thumb) {
        this.id = id;
        this.code = code;
        this.username = username;
        this.uphoto = uphoto;
        this.uid = uid;
        this.shopid = shopid;
        this.shopname = shopname;
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
        this.sid = sid;
        this.cateid = cateid;
        this.brandid = brandid;
        this.title = title;
        this.title_style = title_style;
        this.title2 = title2;
        this.money = money;
        this.yunjiage = yunjiage;
        this.zongrenshu = zongrenshu;
        this.canyurenshu = canyurenshu;
        this.shenyurenshu = shenyurenshu;
        this.def_renshu = def_renshu;
        this.qishu = qishu;
        this.maxqishu = maxqishu;
        this.thumb = thumb;
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

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getBrandid() {
        return brandid;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_style() {
        return title_style;
    }

    public void setTitle_style(String title_style) {
        this.title_style = title_style;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getYunjiage() {
        return yunjiage;
    }

    public void setYunjiage(String yunjiage) {
        this.yunjiage = yunjiage;
    }

    public String getZongrenshu() {
        return zongrenshu;
    }

    public void setZongrenshu(String zongrenshu) {
        this.zongrenshu = zongrenshu;
    }

    public String getCanyurenshu() {
        return canyurenshu;
    }

    public void setCanyurenshu(String canyurenshu) {
        this.canyurenshu = canyurenshu;
    }

    public String getShenyurenshu() {
        return shenyurenshu;
    }

    public void setShenyurenshu(String shenyurenshu) {
        this.shenyurenshu = shenyurenshu;
    }

    public String getDef_renshu() {
        return def_renshu;
    }

    public void setDef_renshu(String def_renshu) {
        this.def_renshu = def_renshu;
    }

    public String getQishu() {
        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public String getMaxqishu() {
        return maxqishu;
    }

    public void setMaxqishu(String maxqishu) {
        this.maxqishu = maxqishu;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Override
    public String toString() {
        return "ListItems{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", username='" + username + '\'' +
                ", uphoto='" + uphoto + '\'' +
                ", uid='" + uid + '\'' +
                ", shopid='" + shopid + '\'' +
                ", shopname='" + shopname + '\'' +
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
                ", sid='" + sid + '\'' +
                ", cateid='" + cateid + '\'' +
                ", brandid='" + brandid + '\'' +
                ", title='" + title + '\'' +
                ", title_style='" + title_style + '\'' +
                ", title2='" + title2 + '\'' +
                ", money='" + money + '\'' +
                ", yunjiage='" + yunjiage + '\'' +
                ", zongrenshu='" + zongrenshu + '\'' +
                ", canyurenshu='" + canyurenshu + '\'' +
                ", shenyurenshu='" + shenyurenshu + '\'' +
                ", def_renshu='" + def_renshu + '\'' +
                ", qishu='" + qishu + '\'' +
                ", maxqishu='" + maxqishu + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }
}
