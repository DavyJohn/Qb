package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/22.
 */
public class RechargeData {

    private String title;
    private String amount;
    private String out_trade_no;
    private String sign;

    public RechargeData(String title, String amount, String out_trade_no, String sign) {
        this.title = title;
        this.amount = amount;
        this.out_trade_no = out_trade_no;
        this.sign = sign;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "RechargeData{" +
                "title='" + title + '\'' +
                ", amount='" + amount + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
