package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/23.
 */
public class ChangeNameResultInfo {

    private String rst;
    private String msg;

    public ChangeNameResultInfo(String rst, String msg) {
        this.rst = rst;
        this.msg = msg;
    }

    public String getRst() {
        return rst;
    }

    public void setRst(String rst) {
        this.rst = rst;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ChangeNameResultInfo{" +
                "rst='" + rst + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
