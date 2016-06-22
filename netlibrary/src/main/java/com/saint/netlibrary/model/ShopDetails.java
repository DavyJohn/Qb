package com.saint.netlibrary.model;

import java.util.List;

/**
 * Created by 志浩 on 2016/6/21.
 */
public class ShopDetails  {

    public ShopDetailsItem item;
    public ShopDetailsGorecode lastgorecode;
    public List<ShopDetailsQData> loopqishu;
    public ShopDetailsCategory category;
    public SHopDetailBrand brand;

    public ShopDetails(ShopDetailsItem item, ShopDetailsGorecode lastgorecode, List<ShopDetailsQData> loopqishu, ShopDetailsCategory category, SHopDetailBrand brand) {
        this.item = item;
        this.lastgorecode = lastgorecode;
        this.loopqishu = loopqishu;
        this.category = category;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ShopDetails{" +
                "loopqishu=" + loopqishu +
                '}';
    }

    public SHopDetailBrand getBrand() {
        return brand;
    }

    public void setBrand(SHopDetailBrand brand) {
        this.brand = brand;
    }

    public ShopDetailsCategory getCategory() {
        return category;
    }

    public void setCategory(ShopDetailsCategory category) {
        this.category = category;
    }

    public ShopDetailsGorecode getLastgorecode() {
        return lastgorecode;
    }

    public void setLastgorecode(ShopDetailsGorecode lastgorecode) {
        this.lastgorecode = lastgorecode;
    }

    public ShopDetailsItem getItem() {
        return item;
    }

    public void setItem(ShopDetailsItem item) {
        this.item = item;
    }
}
