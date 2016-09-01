package com.saint.netlibrary.model;

import java.util.List;

/**
 * Created by 志浩 on 2016/8/23.
 */
public class XIaoFeiJilu {

    private List<ItmesData> listItems;
    private String code;
    private String count;

    public XIaoFeiJilu(List<ItmesData> listItems, String code, String count) {
        this.listItems = listItems;
        this.code = code;
        this.count = count;
    }

    public List<ItmesData> getListItems() {
        return listItems;
    }

    public void setListItems(List<ItmesData> listItems) {
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

    @Override
    public String toString() {
        return "XIaoFeiJilu{" +
                "listItems=" + listItems +
                ", code='" + code + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
