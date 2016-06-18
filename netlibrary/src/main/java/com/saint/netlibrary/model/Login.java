package com.saint.netlibrary.model;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class Login  {

    public  int state;

    @Override
    public String toString() {
        return "Login{" +
                "state=" + state +
                '}';
    }

    public Login(int state) {
        this.state = state;
    }
}
