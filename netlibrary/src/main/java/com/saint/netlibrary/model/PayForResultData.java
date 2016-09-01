package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/25.
 */
public class PayForResultData {

    private Double MoneyCount;
    private Double Money;
    private int ShopNum;
    private String submitcode;

    public PayForResultData(Double moneyCount, Double money, int shopNum, String submitcode) {
        MoneyCount = moneyCount;
        Money = money;
        ShopNum = shopNum;
        this.submitcode = submitcode;
    }

    public Double getMoneyCount() {
        return MoneyCount;
    }

    public void setMoneyCount(Double moneyCount) {
        MoneyCount = moneyCount;
    }

    public Double getMoney() {
        return Money;
    }

    public void setMoney(Double money) {
        Money = money;
    }

    public int getShopNum() {
        return ShopNum;
    }

    public void setShopNum(int shopNum) {
        ShopNum = shopNum;
    }

    public String getSubmitcode() {
        return submitcode;
    }

    public void setSubmitcode(String submitcode) {
        this.submitcode = submitcode;
    }

    @Override
    public String toString() {
        return "PayForResultData{" +
                "MoneyCount=" + MoneyCount +
                ", Money=" + Money +
                ", ShopNum=" + ShopNum +
                ", submitcode='" + submitcode + '\'' +
                '}';
    }
}
