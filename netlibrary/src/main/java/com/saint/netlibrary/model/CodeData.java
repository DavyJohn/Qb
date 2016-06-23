package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/6/23.
 */

public class CodeData {
     public int code;
     public String regtype;
     public String check_code;
     public String message;

    public CodeData() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCheck_code() {
        return check_code;
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }

    public String getRegtype() {
        return regtype;
    }

    public void setRegtype(String regtype) {
        this.regtype = regtype;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CodeData{" +
                "code=" + code +
                ", regtype='" + regtype + '\'' +
                ", check_code='" + check_code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
