package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/20.
 */
public class AddShopCarResult {

    private String code;
    private String num;

    public AddShopCarResult(String code, String num) {
        this.code = code;
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "AddShopCarResult{" +
                "code='" + code + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
