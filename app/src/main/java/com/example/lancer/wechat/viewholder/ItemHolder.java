package com.example.lancer.wechat.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lancer.wechat.R;

/**
 * Created by Lancer on 2018/4/15.
 */

public class ItemHolder extends RecyclerView.ViewHolder {
    public static TextView tvtitle;
    public static TextView tvcontent;
    public static TextView tvtime;
    public static ImageView ivwechat;

    public ItemHolder(View itemView) {
        super(itemView);
        tvcontent = itemView.findViewById(R.id.tv_item_content);
        tvtitle = itemView.findViewById(R.id.tv_item_title);
        tvtime = itemView.findViewById(R.id.tv_item_time);
        ivwechat = itemView.findViewById(R.id.iv_item_wechat);

    }
}
