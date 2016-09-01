package com.saint.netlibrary.model;

import java.util.List;

/**
 * Created by 志浩 on 2016/8/19.
 */
public class MyQbJu {

    private List<ListItems> listItems;
    private String code;
    private String count;

    public MyQbJu(List<ListItems> listItems, String code, String count) {
        this.listItems = listItems;
        this.code = code;
        this.count = count;
    }

    public List<ListItems> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItems> listItems) {
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
        return "MyQbJu{" +
                "listItems=" + listItems +
                ", code='" + code + '\'' +
                ", count='" + count + '\'' +
                '}';
    }


}
