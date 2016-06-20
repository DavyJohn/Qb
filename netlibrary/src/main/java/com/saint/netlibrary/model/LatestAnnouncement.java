package com.saint.netlibrary.model;

import java.util.List;

/**
 * 最新揭晓
 * Created by 志浩 on 2016/6/20.
 */
public class LatestAnnouncement {

    public List<ListItemsData> listItems;

    public LatestAnnouncement(List<ListItemsData> listItems) {
        this.listItems = listItems;
    }

    @Override
    public String toString() {
        return "LatestAnnouncement{" +
                "listItems=" + listItems +
                '}';
    }
}
