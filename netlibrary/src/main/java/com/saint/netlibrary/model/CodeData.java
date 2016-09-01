package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/6/23.
 */

public class CodeData {
     private int code;
     private String regtype;
     private String check_code;

    public CodeData(int code, String regtype, String check_code) {
        this.code = code;
        this.regtype = regtype;
        this.check_code = check_code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRegtype() {
        return regtype;
    }

    public void setRegtype(String regtype) {
        this.regtype = regtype;
    }

    public String getCheck_code() {
        return check_code;
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }

    @Override
    public String toString() {
        return "CodeData{" +
                "code=" + code +
                ", regtype='" + regtype + '\'' +
                ", check_code='" + check_code + '\'' +
                '}';
    }
}
