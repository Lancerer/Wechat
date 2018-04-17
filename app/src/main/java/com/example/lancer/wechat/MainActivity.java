package com.example.lancer.wechat;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lancer.wechat.adapter.MyViewPageAdapter;
import com.example.lancer.wechat.fragment.ContactlistFragemnt;
import com.example.lancer.wechat.fragment.FindFragment;
import com.example.lancer.wechat.fragment.MyFragment;
import com.example.lancer.wechat.fragment.WechatFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.iv_pius)
    ImageView ivPius;
    @Bind(R.id.tab)
    TabLayout Tab;
    @Bind(R.id.pager)
    ViewPager pager;


    private String[] text = {"wechat", "contact", "find", "my"};
    private int[] img = {R.drawable.select_wechat, R.drawable.select_list, R.drawable.select_find, R.drawable.select_my};
    private String[] listdata = {"发起群聊", "添加朋友", "扫一扫", "收付款", "帮助与反馈"};
    private ArrayList<Map<String, Integer>> list;
    private ArrayList<Fragment> fragments;
    private MyFragment myFragment;
    private ContactlistFragemnt contactlistFragemnt;
    private FindFragment findFragment;
    private WechatFragment wechatFragment;
    private PopupWindow pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initTab();

    }

    private void initData() {
        final ListView listView = new ListView(MainActivity.this);
        list = new ArrayList<>();
        Map map = new HashMap<String, Integer>();
        map.put("name", "发起群聊");
        map.put("Icon", R.drawable.icon_menu_group);
        list.add(map);
        map = new HashMap<String, Integer>();
        map.put("name", "添加朋友");
        map.put("Icon", R.drawable.icon_menu_addfriend);
        list.add(map);
        map = new HashMap<String, Integer>();
        map.put("name", "扫一扫");
        map.put("Icon", R.drawable.icon_menu_sao);
        list.add(map);
        map = new HashMap<String, Integer>();
        map.put("name", "收付款");
        map.put("Icon", R.drawable.icon_more);
        list.add(map);
        map = new HashMap<String, Integer>();
        map.put("name", "帮助与反馈");
        map.put("Icon", R.drawable.icon_talk);
        list.add(map);

        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, list, R.layout.pop_item, new String[]{"Icon", "name"}, new int[]{R.id.pop_iv, R.id.pop_tv});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (pop != null & pop.isShowing()) {
                    pop.dismiss();
                    pop = null;
                    Toast.makeText(MainActivity.this, listdata[position], Toast.LENGTH_SHORT).show();
                }
            }
        });


        ivPius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pop == null) {
                    pop = new PopupWindow(MainActivity.this);
                    pop.setWidth(600);
                    pop.setHeight(700);
                    pop.setContentView(listView);
                    pop.setFocusable(true);

                }
                pop.showAsDropDown(ivPius, -150, 0);

            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,SearchActivity.class), ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });
    }

    private void initTab() {
        Tab.setSelectedTabIndicatorHeight(0);//去除指示器
        for (int i = 0; i < 4; i++) {
            Tab.addTab(Tab.newTab().setCustomView(createView(img[i], text[i])));
        }
        initFragment();
        pager.setAdapter(new MyViewPageAdapter(getSupportFragmentManager(), fragments));
        Tab.addOnTabSelectedListener(this);
       /* Tab.setupWithViewPager(pager);关联ciewpager  newTab（）就会失效*/
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        myFragment = new MyFragment();
        contactlistFragemnt = new ContactlistFragemnt();
        findFragment = new FindFragment();
        wechatFragment = new WechatFragment();
        fragments.add(wechatFragment);
        fragments.add(contactlistFragemnt);
        fragments.add(findFragment);
        fragments.add(myFragment);
    }

    //初始化Tab布局
    private View createView(int icon, String tab) {
        View view = View.inflate(this, R.layout.tab_item, null);
        ImageView imageView = view.findViewById(R.id.icon);
        TextView title = view.findViewById(R.id.title);
        imageView.setImageResource(icon);
        title.setText(tab);
        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
