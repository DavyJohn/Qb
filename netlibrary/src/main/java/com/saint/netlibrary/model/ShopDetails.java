package com.saint.netlibrary.model;

import java.util.List;

/**
 * Created by 志浩 on 2016/6/21.
 */
public class ShopDetails  {

    private ShopDetailsItem item;
    private ShopDetailsGorecode lastgorecode;
    private List<ShopDetailsQData> loopqishu;
    private ShopDetailsCategory category;
    private SHopDetailBrand brand;

    private String user_shop_time;
    private String user_shop_number;
    private String user_shop_codes;

    public ShopDetails(ShopDetailsItem item, ShopDetailsGorecode lastgorecode, List<ShopDetailsQData> loopqishu, ShopDetailsCategory category, SHopDetailBrand brand, String user_shop_time, String user_shop_number, String user_shop_codes) {
        this.item = item;
        this.lastgorecode = lastgorecode;
        this.loopqishu = loopqishu;
        this.category = category;
        this.brand = brand;
        this.user_shop_time = user_shop_time;
        this.user_shop_number = user_shop_number;
        this.user_shop_codes = user_shop_codes;
    }

    public ShopDetailsItem getItem() {
        return item;
    }

    public void setItem(ShopDetailsItem item) {
        this.item = item;
    }

    public ShopDetailsGorecode getLastgorecode() {
        return lastgorecode;
    }

    public void setLastgorecode(ShopDetailsGorecode lastgorecode) {
        this.lastgorecode = lastgorecode;
    }

    public List<ShopDetailsQData> getLoopqishu() {
        return loopqishu;
    }

    public void setLoopqishu(List<ShopDetailsQData> loopqishu) {
        this.loopqishu = loopqishu;
    }

    public ShopDetailsCategory getCategory() {
        return category;
    }

    public void setCategory(ShopDetailsCategory category) {
        this.category = category;
    }

    public SHopDetailBrand getBrand() {
        return brand;
    }

    public void setBrand(SHopDetailBrand brand) {
        this.brand = brand;
    }

    public String getUser_shop_time() {
        return user_shop_time;
    }

    public void setUser_shop_time(String user_shop_time) {
        this.user_shop_time = user_shop_time;
    }

    public String getUser_shop_number() {
        return user_shop_number;
    }

    public void setUser_shop_number(String user_shop_number) {
        this.user_shop_number = user_shop_number;
    }

    public String getUser_shop_codes() {
        return user_shop_codes;
    }

    public void setUser_shop_codes(String user_shop_codes) {
        this.user_shop_codes = user_shop_codes;
    }

    @Override
    public String toString() {
        return "ShopDetails{" +
                "item=" + item +
                ", lastgorecode=" + lastgorecode +
                ", loopqishu=" + loopqishu +
                ", category=" + category +
                ", brand=" + brand +
                ", user_shop_time='" + user_shop_time + '\'' +
                ", user_shop_number='" + user_shop_number + '\'' +
                ", user_shop_codes='" + user_shop_codes + '\'' +
                '}';
    }
}
