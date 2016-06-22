package com.saint.netlibrary.model;

import java.util.List;

/**
 * 最新揭晓
 * Created by 志浩 on 2016/6/20.
 */
public class LatestAnnouncement {

    public List<ListItemsData> listItems;
    public String code;
    public String count;

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
}
