package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/8/16.
 */
public class Abcd {
    private CodeData abcd;

    public Abcd(CodeData abcd) {
        this.abcd = abcd;
    }

    public CodeData getAbcd() {
        return abcd;
    }

    @Override
    public String toString() {
        return "Abcd{" +
                "abcd=" + abcd +
                '}';
    }
}
