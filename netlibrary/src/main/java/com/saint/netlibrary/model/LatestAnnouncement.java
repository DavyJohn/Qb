package com.saint.netlibrary.model;

import java.util.List;

/**
 * 最新揭晓
 * Created by 志浩 on 2016/6/20.
 */
public class LatestAnnouncement {

    private List<ListItemsData> listItems;
    private String code;
    private String count;

    public LatestAnnouncement(List<ListItemsData> listItems, String code, String count) {
        this.listItems = listItems;
        this.code = code;
        this.count = count;
    }

    @Override
    public String toString() {
        return "LatestAnnouncement{" +
                "listItems=" + listItems +
                ", code='" + code + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public List<ListItemsData> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItemsData> listItems) {
        this.listItems = listItems;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
