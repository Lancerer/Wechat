package com.example.lancer.wechat.model;

/**
 * Created by Lancer on 2018/4/15.
 */

public class RobatBean {
    public static final  int SEND=1;
    public static  final int RECIVER=2;
    public String text;
    public int flag;

    public String getText() {
        return text;
    }

    public RobatBean(String text,int flag) {
        this.text = text;
        this.flag=flag;
    }

    public void setText(String text) {

        this.text = text;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
