package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/19.
 */
public class AddressDataItem {

    private String id;
    private String jiedao;
    private String mobile;
    private String sheng;
    private String shi;
    private String shouhuoren;
    private String tell;
    private String time;
    private String uid;
    private String xian;
    private String youbian;

    public AddressDataItem(String id, String jiedao, String mobile, String sheng, String shi, String shouhuoren, String tell, String time, String uid, String xian, String youbian) {
        this.id = id;
        this.jiedao = jiedao;
        this.mobile = mobile;
        this.sheng = sheng;
        this.shi = shi;
        this.shouhuoren = shouhuoren;
        this.tell = tell;
        this.time = time;
        this.uid = uid;
        this.xian = xian;
        this.youbian = youbian;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJiedao() {
        return jiedao;
    }

    public void setJiedao(String jiedao) {
        this.jiedao = jiedao;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getShouhuoren() {
        return shouhuoren;
    }

    public void setShouhuoren(String shouhuoren) {
        this.shouhuoren = shouhuoren;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getYoubian() {
        return youbian;
    }

    public void setYoubian(String youbian) {
        this.youbian = youbian;
    }

    @Override
    public String toString() {
        return "AddressDataItem{" +
                "id='" + id + '\'' +
                ", jiedao='" + jiedao + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sheng='" + sheng + '\'' +
                ", shi='" + shi + '\'' +
                ", shouhuoren='" + shouhuoren + '\'' +
                ", tell='" + tell + '\'' +
                ", time='" + time + '\'' +
                ", uid='" + uid + '\'' +
                ", xian='" + xian + '\'' +
                ", youbian='" + youbian + '\'' +
                '}';
    }
}
