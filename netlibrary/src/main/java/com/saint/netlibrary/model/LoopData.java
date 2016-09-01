package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/19.
 */
public class LoopData {

    private String url ;
    private String title;

    public LoopData(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "LoopData{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
