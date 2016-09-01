package com.saint.netlibrary.model;

import java.util.List;

/**
 * Created by 志浩 on 2016/8/13.
 */
public class BannerData {

    private String state;
    private List<BannerListItems> listItems;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<BannerListItems> getListItems() {
        return listItems;
    }

    public void setListItems(List<BannerListItems> listItems) {
        this.listItems = listItems;
    }

    public BannerData(String state, List<BannerListItems> listItems) {
        this.state = state;
        this.listItems = listItems;
    }

    @Override
    public String toString() {
        return "BannerData{" +
                "state='" + state + '\'' +
                ", listItems=" + listItems +
                '}';
    }
}
