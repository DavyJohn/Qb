package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/6/23.
 */
public class MobileCheck {

    public int code;
    public String message;

    public MobileCheck(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "MobileCheck{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
