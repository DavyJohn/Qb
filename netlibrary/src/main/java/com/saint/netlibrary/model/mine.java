package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class mine {
    public String uid;
    public String username;
    public String email;
    public String mobile;
    public String user_ip;
    public String img;
    public String qianming;
    public String money;
    public int  emailcode;
    public int mobilecode;
    public int passcode;
    public String reg_key;
    public String score;
    public String jingyan;
    public String yaoqing;
    public String band;
    public String yungoudj;
    public String time;

    public mine(String uid, String username, String email, String mobile, String user_ip, String img, String qianming, String money, int emailcode, int mobilecode, int passcode, String reg_key, String score, String jingyan, String yaoqing, String band, String yungoudj, String time) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.user_ip = user_ip;
        this.img = img;
        this.qianming = qianming;
        this.money = money;
        this.emailcode = emailcode;
        this.mobilecode = mobilecode;
        this.passcode = passcode;
        this.reg_key = reg_key;
        this.score = score;
        this.jingyan = jingyan;
        this.yaoqing = yaoqing;
        this.band = band;
        this.yungoudj = yungoudj;
        this.time = time;
    }

    @Override
    public String toString() {
        return "mine{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", user_ip='" + user_ip + '\'' +
                ", img='" + img + '\'' +
                ", qianming='" + qianming + '\'' +
                ", money='" + money + '\'' +
                ", emailcode=" + emailcode +
                ", mobilecode=" + mobilecode +
                ", passcode=" + passcode +
                ", reg_key='" + reg_key + '\'' +
                ", score='" + score + '\'' +
                ", jingyan='" + jingyan + '\'' +
                ", yaoqing='" + yaoqing + '\'' +
                ", band='" + band + '\'' +
                ", yungoudj='" + yungoudj + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
