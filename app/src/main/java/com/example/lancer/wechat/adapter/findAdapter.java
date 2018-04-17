package com.example.lancer.wechat.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lancer.wechat.R;

/**
 * Created by Lancer on 2018/4/16.
 */

public class findAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private int[] imgs = {R.drawable.v2, R.drawable.icon_de_saoyisao, R.drawable.icon_de_yao, R.drawable.icon_de_nearby, R.drawable.icon_de_ping, R.drawable.icon_de_shop, R.drawable.icon_de_game};
    private String[] names = {"朋友圈", "扫一扫", "摇一摇", "附近的人", "漂流瓶", "购物", "游戏"};
    private static final int TYPEBLACK = 1;
    private static final int TYPEITEM = 2;
    Context mContext;

    public findAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPEBLACK) {
            View view = View.inflate(mContext, R.layout.item_find, null);
            blackHolder holder = new blackHolder(view);
            view.setOnClickListener(this);
            return holder;
        } else if (viewType == TYPEITEM) {
            View view = View.inflate(mContext, R.layout.item_contact, null);
            ItemHolder holder = new ItemHolder(view);
            view.setOnClickListener(this);
            return holder;
        }
        return null;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof blackHolder) {
            blackHolder holder1 = (blackHolder) holder;
            holder1.llfind.setBackgroundResource(Color.BLACK);
            //点击事件获取position
            holder1.itemView.setTag(position);
        } else if (holder instanceof ItemHolder) {
            ItemHolder holder1 = (ItemHolder) holder;
            holder1.ivfind.setImageResource(imgs[position]);
            holder1.contactname.setText(names[position]);
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    @Override
    public int getItemViewType(int position) {
        if ((position + 1 % 2) == 0) {
            return TYPEBLACK;
        } else {
            return TYPEITEM;
        }
    }

    public class blackHolder extends RecyclerView.ViewHolder {
        LinearLayout llfind;

        public blackHolder(View itemView) {
            super(itemView);
            llfind = itemView.findViewById(R.id.ll_find);
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView contactname;
        ImageView ivfind;

        public ItemHolder(View itemView) {
            super(itemView);
            contactname = itemView.findViewById(R.id.contact_name);
            ivfind = itemView.findViewById(R.id.iv_find);
        }
    }

    private OnItemClickLinter mOnItemClickLinter = null;

    public static interface OnItemClickLinter {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickLinter != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickLinter.onItemClick(v, (int) v.getTag());
        }
    }

    //最后暴露给外面的调用者，定义一个设置Listener的方法（）：
    public void setOnItemClickListener(OnItemClickLinter listener) {
        this.mOnItemClickLinter = listener;
    }
}
