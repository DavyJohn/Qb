package com.saint.netlibrary.model;

import retrofit2.http.PUT;

/**
 * Created by 志浩 on 2016/6/22.
 */
public class ShopDetailsGorecode {

    public String id;
    public String code;
    public String code_tmp;
    public String username;
    public String uphoto;
    public String uid;
    public String shopid;
    public String shopname;
    public String shopqishu;
    public String gonumber;
    public String goucode;
    public String moneycount;
    public String huode;
    public String pay_type;
    public String ip;
    public String status;
    public String time;
    public String company;
    public String company_code;
    public String company_money;

    public ShopDetailsGorecode(String id, String code, String code_tmp, String username, String uphoto, String uid, String shopid, String shopname, String shopqishu, String gonumber, String goucode, String moneycount, String huode, String pay_type, String ip, String status, String time, String company, String company_code, String company_money) {
        this.id = id;
        this.code = code;
        this.code_tmp = code_tmp;
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
    }

    @Override
    public String toString() {
        return "ShopDetailsGorecode{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", code_tmp='" + code_tmp + '\'' +
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
                '}';
    }
}
