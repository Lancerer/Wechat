package com.example.lancer.wechat.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lancer.wechat.R;
import com.example.lancer.wechat.RobatActivity;
import com.example.lancer.wechat.adapter.wechatAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WechatFragment extends Fragment {

    @Bind(R.id.re_wechat)
    RecyclerView reWechat;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<String> list;
    private wechatAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        list = new ArrayList<>();
        //准备数据源
        for (int i = 0; i < 10; i++) {
            list.add("MyFriend" + i);
        }
        //设置RecycleView的条目布局管理器
        reWechat.setLayoutManager(linearLayoutManager);
        reWechat.setHasFixedSize(true);
        adapter = new wechatAdapter(getContext(), list);
        reWechat.setAdapter(adapter);
        //RecycleView的点击事件
        adapter.setOnItemClickListener(new wechatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), RobatActivity.class);
                //将聊天对象的名称传给具体聊天界面
                intent.putExtra("chatname", list.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
