package com.example.lancer.wechat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Lancer on 2018/4/14.
 */

public class MyViewPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private String[] text = {"微信", "通讯录", "发现", "我"};
    public MyViewPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return text[position];
    }
}
