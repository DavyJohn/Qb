package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/6/16.
 */

public class Mine {

    private String uid;
    private String username;
    private String email;
    private String mobile;
    private String user_ip;
    private String img;
    private String qianming;
    private String groupid;
    private String money;
    private String addgroup;
    private String emailcode;
    private String mobilecode;
    private String reg_key;
    private String score;
    private String jingyan;
    private String yaoqing;
    private String band;
    private String time;
    private String yungoudj;

    public Mine(String uid, String username, String email, String mobile, String user_ip, String img, String qianming, String groupid, String money, String addgroup, String emailcode, String mobilecode, String reg_key, String score, String jingyan, String yaoqing, String band, String time, String yungoudj) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.user_ip = user_ip;
        this.img = img;
        this.qianming = qianming;
        this.groupid = groupid;
        this.money = money;
        this.addgroup = addgroup;
        this.emailcode = emailcode;
        this.mobilecode = mobilecode;
        this.reg_key = reg_key;
        this.score = score;
        this.jingyan = jingyan;
        this.yaoqing = yaoqing;
        this.band = band;
        this.time = time;
        this.yungoudj = yungoudj;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getQianming() {
        return qianming;
    }

    public void setQianming(String qianming) {
        this.qianming = qianming;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAddgroup() {
        return addgroup;
    }

    public void setAddgroup(String addgroup) {
        this.addgroup = addgroup;
    }

    public String getEmailcode() {
        return emailcode;
    }

    public void setEmailcode(String emailcode) {
        this.emailcode = emailcode;
    }

    public String getMobilecode() {
        return mobilecode;
    }

    public void setMobilecode(String mobilecode) {
        this.mobilecode = mobilecode;
    }

    public String getReg_key() {
        return reg_key;
    }

    public void setReg_key(String reg_key) {
        this.reg_key = reg_key;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getJingyan() {
        return jingyan;
    }

    public void setJingyan(String jingyan) {
        this.jingyan = jingyan;
    }

    public String getYaoqing() {
        return yaoqing;
    }

    public void setYaoqing(String yaoqing) {
        this.yaoqing = yaoqing;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYungoudj() {
        return yungoudj;
    }

    public void setYungoudj(String yungoudj) {
        this.yungoudj = yungoudj;
    }

    @Override
    public String toString() {
        return "Mine{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", user_ip='" + user_ip + '\'' +
                ", img='" + img + '\'' +
                ", qianming='" + qianming + '\'' +
                ", groupid='" + groupid + '\'' +
                ", money='" + money + '\'' +
                ", addgroup='" + addgroup + '\'' +
                ", emailcode='" + emailcode + '\'' +
                ", mobilecode='" + mobilecode + '\'' +
                ", reg_key='" + reg_key + '\'' +
                ", score='" + score + '\'' +
                ", jingyan='" + jingyan + '\'' +
                ", yaoqing='" + yaoqing + '\'' +
                ", band='" + band + '\'' +
                ", time='" + time + '\'' +
                ", yungoudj='" + yungoudj + '\'' +
                '}';
    }
}
