package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/6/22.
 */
public class ShopDetailsQData {

    public String url;
    public String title;

    public ShopDetailsQData(String url, String title) {
        this.url = url;
        this.title = title;
    }

    @Override
    public String toString() {
        return "ShopDetailsQData{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
