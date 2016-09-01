package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/26.
 */
public class DelCartItemResult {

    private String code;

    public DelCartItemResult(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DelCartItemResult{" +
                "code='" + code + '\'' +
                '}';
    }
}
