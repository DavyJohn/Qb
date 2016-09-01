package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/23.
 */
public class Avatar {

    private String rst;

    private String msg;
    private String filename;

    public Avatar(String rst, String msg, String filename) {
        this.rst = rst;
        this.msg = msg;
        this.filename = filename;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "rst='" + rst + '\'' +
                ", msg='" + msg + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
