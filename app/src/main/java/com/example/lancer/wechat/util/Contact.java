package com.example.lancer.wechat.util;

import java.io.Serializable;

/**
 * Created by Lancer on 2018/4/16.
 */
public class Contact implements Serializable {
    private String mName;
    private int mType;

    public Contact(String name, int type) {
        mName = name;
        mType = type;
    }

    public String getmName() {
        return mName;
    }

    public int getmType() {
        return mType;
    }

}