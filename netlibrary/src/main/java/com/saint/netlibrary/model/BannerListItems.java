package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/13.
 */
public class BannerListItems {

    private String alt;
    private String url;
    private String src;
    private String width;
    private String height;

    public String getAlt() {
        return alt;
    }

    public String getUrl() {
        return url;
    }

    public String getSrc() {
        return src;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "BannerListItems{" +
                "alt='" + alt + '\'' +
                ", url='" + url + '\'' +
                ", src='" + src + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }

    public BannerListItems(String alt, String url, String src, String width, String height) {
        this.alt = alt;
        this.url = url;
        this.src = src;
        this.width = width;
        this.height = height;
    }
}
