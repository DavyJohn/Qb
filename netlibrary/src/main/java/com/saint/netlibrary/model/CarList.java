package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/6/21.
 */
public class CarList {

    public String cateid;
    public String parentid;
    public String channel;
    public String model;
    public String name;
    public String catdir;
    public String url;
    public String info;
    public String order;

    public CarList(String cateid, String parentid, String channel, String model, String name, String catdir, String url, String info, String order) {
        this.cateid = cateid;
        this.parentid = parentid;
        this.channel = channel;
        this.model = model;
        this.name = name;
        this.catdir = catdir;
        this.url = url;
        this.info = info;
        this.order = order;
    }

    @Override
    public String toString() {
        return "CarList{" +
                "cateid='" + cateid + '\'' +
                ", parentid='" + parentid + '\'' +
                ", channel='" + channel + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", catdir='" + catdir + '\'' +
                ", url='" + url + '\'' +
                ", info='" + info + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
