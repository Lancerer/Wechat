package com.example.lancer.wechat.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lancer.wechat.R;
import com.example.lancer.wechat.adapter.findAdapter;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FindFragment extends Fragment {
/*
    @Bind(R.id.re_find)
    RecyclerView reFind;
    private LinearLayoutManager linearLayoutManager;
    private findAdapter adapter;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, null);
        ButterKnife.bind(this, view);
        return view;
    }

  /*  private void initData() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new findAdapter(getContext());
        reFind.setLayoutManager(linearLayoutManager);
        reFind.setAdapter(adapter);
        adapter.setOnItemClickListener(new findAdapter.OnItemClickLinter() {
            @Override
            public void onItemClick(View view, int position) {
                //发送角标
                EventBus.getDefault().post(position);

            }
        });
    }*/

  /*  @Override
    public void onDestroyView() {

        super.onDestroyView();
        ButterKnife.unbind(this);
    }*/
}
