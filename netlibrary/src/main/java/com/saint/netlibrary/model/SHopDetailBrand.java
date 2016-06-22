package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/6/22.
 */
public class SHopDetailBrand {

    public String id;
    public String cateid;
    public String status;
    public String name;
    public String order;

    public SHopDetailBrand(String id, String cateid, String status, String name, String order) {
        this.id = id;
        this.cateid = cateid;
        this.status = status;
        this.name = name;
        this.order = order;
    }

    @Override
    public String toString() {
        return "SHopDetailBrand{" +
                "id='" + id + '\'' +
                ", cateid='" + cateid + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
