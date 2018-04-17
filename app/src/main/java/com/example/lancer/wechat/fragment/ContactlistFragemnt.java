package com.example.lancer.wechat.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lancer.wechat.DividerItemDecoration;
import com.example.lancer.wechat.R;
import com.example.lancer.wechat.adapter.ContactAdapter;
import com.example.lancer.wechat.friendActivity;
import com.example.lancer.wechat.view.LetterView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactlistFragemnt extends Fragment {


    @Bind(R.id.letter_view)
    LetterView letterView;
    @Bind(R.id.re_list)
    RecyclerView reList;

    private String[] contactNames;
    private LinearLayoutManager layoutManager;

    private ContactAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactlist, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        contactNames = new String[]{"王境泽","狗杰","王盛梁","谢月奎","付徐信","徐林波","善阳", "洪磊", "奈落", "桔梗", "杀生丸", "犬夜叉", "犬冢牙", "千手柱间", "迈特凯", "迪达拉", "宇智波斑", "波风水门", "奈良鹿丸", "漩涡长门", "宇智波鼬", "日向宁次", "日向雏田", "旗木卡卡西", "我爱罗", "宇智波佐助", "春野樱", "漩涡鸣人", "acher", "break", "even", "float", "i LIKE", "KK", "NOBAY", "$6", "穆人清", "陈圆圆", "郭芙", "郭襄", "穆念慈", "东方不败", "梅超风", "林平之", "林远图", "灭绝师太", "段誉", "鸠摩智"};

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new ContactAdapter(getContext(), contactNames);

        reList.setLayoutManager(layoutManager);
        reList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        reList.setAdapter(adapter);

        letterView.setCharacterListener(new LetterView.CharacterClickListener() {
            @Override
            public void clickCharacter(String character) {
                layoutManager.scrollToPositionWithOffset(adapter.getScrollPosition(character), 0);
            }

            @Override
            public void clickArrow() {
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        });
        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickLinter() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), friendActivity.class);
                intent.putExtra("names", adapter.resultList.get(position).getmName());
                startActivity(intent);
             /*   EventBus.getDefault().post(contactNames[position]);*/
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
