package com.example.lancer.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

public class friendActivity extends AppCompatActivity {


    @Bind(R.id.iv_robat_cancel)
    ImageView ivRobatCancel;
    @Bind(R.id.tv_robat_name)
    TextView tvRobatName;
    @Bind(R.id.iv_robat_chat)
    ImageView ivRobatChat;
    @Bind(R.id.iv_friend)
    ImageView ivFriend;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.wechat_number)
    TextView wechatNumber;
    @Bind(R.id.wechat_name)
    TextView wechatName;
    @Bind(R.id.bt_sendmsg)
    Button btSendmsg;
    @Bind(R.id.shiping)
    Button shiping;
    private String names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        ButterKnife.bind(this);
        names = getIntent().getStringExtra("names");
        tvName.setText(names);
        wechatName.setText("昵称：" + names + "真德秀");
        wechatNumber.setText("微信号：" + 5438438);
        btSendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(names);
                Intent intent = new Intent(friendActivity.this, RobatActivity.class);
          /*      intent.putExtra("friendname", names);*/
                startActivity(intent);
            }
        });
        shiping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(friendActivity.this, HongleiActivity.class);
                intent.putExtra("tohonglei", names);
                startActivity(intent);
            }
        });
    }


  /*  @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }*/
}
