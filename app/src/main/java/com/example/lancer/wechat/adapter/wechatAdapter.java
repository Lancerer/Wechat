package com.example.lancer.wechat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.lancer.wechat.R;
import com.example.lancer.wechat.viewholder.ItemHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class wechatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    Context mContext;
    ArrayList<String> list;
    private int[] icon = {R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4, R.drawable.icon5, R.drawable.icon6, R.drawable.icon7, R.drawable.icon8, R.drawable.icon9, R.drawable.icon10
    };

    public wechatAdapter(Context context, ArrayList<String> list) {
        this.list = list;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.wechat_item, null);
        ItemHolder holder = new ItemHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder) holder;
        //格式化时间
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        itemHolder.tvtime.setText(format.format(date));
        itemHolder.tvtitle.setText(list.get(position));
        itemHolder.tvcontent.setText("[Message]");
        itemHolder.ivwechat.setImageResource(icon[position]);
        itemHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private OnItemClickListener mOnItemClickListener = null;

    //在MyAdapter中定义如下接口,模拟ListView的OnItemClickListener
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // 将点击事件转移给外面的调用者
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    //最后暴露给外面的调用者，定义一个设置Listener的方法（）：
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
