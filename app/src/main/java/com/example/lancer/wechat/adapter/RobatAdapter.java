package com.example.lancer.wechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lancer.wechat.R;
import com.example.lancer.wechat.model.RobatBean;

import java.util.List;

/**
 * Created by Lancer on 2018/4/15.
 */

public class RobatAdapter extends BaseAdapter {
    private List<RobatBean> lists;
    private Context mcontext;
    private RelativeLayout layout;

    public RobatAdapter(List<RobatBean> lists, Context mcontext) {
        this.lists = lists;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        if (lists.get(position).getFlag() == RobatBean.RECIVER) {
            layout = (RelativeLayout) inflater.inflate(R.layout.leftitem, null);
        }
        if (lists.get(position).getFlag() == RobatBean.SEND) {
            layout = (RelativeLayout) inflater.inflate(R.layout.rightitem, null);
        }
        TextView tv = layout.findViewById(R.id.tv);
        tv.setText(lists.get(position).getText());
        return layout;
    }
}
