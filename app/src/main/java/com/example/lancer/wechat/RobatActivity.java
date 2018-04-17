package com.example.lancer.wechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lancer.wechat.adapter.RobatAdapter;
import com.example.lancer.wechat.api.Robat_imp;
import com.example.lancer.wechat.model.RobatBean;
import com.example.lancer.wechat.util.HttpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RobatActivity extends AppCompatActivity {

    @Bind(R.id.iv_robat_cancel)
    ImageView ivRobatCancel;
    @Bind(R.id.tv_robat_name)
    TextView tvRobatName;
    @Bind(R.id.iv_robat_chat)
    ImageView icRobatChat;//todo 聊天界面点右上角没实现
    @Bind(R.id.lv_robat)
    ListView lvRobat;
    @Bind(R.id.iv_robat_voice)
    ImageView ivRobatVoice;
    @Bind(R.id.et_robat)
    EditText etRobat;
    @Bind(R.id.iv_robat_emoji)
    ImageView ivRobatEmoji;
    @Bind(R.id.iv_robat_plus)
    ImageView ivRobatPlus;
    private String BaseUrl = "http://www.tuling123.com/";
    private String msg;
    private String url;
    private ArrayList<RobatBean> list;
    private RobatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robat);
        ButterKnife.bind(this);
        initData();
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String name) {
        tvRobatName.setText(name);

    }

    private void initData() {
        String chatname = getIntent().getStringExtra("chatname");
   /*     String friendname = getIntent().getStringExtra("friendname");*/
     /*   tvRobatName.setText(friendname);*/
        tvRobatName.setText(chatname);
        //监听edittext状态
        etRobat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(etRobat.getText().toString())) {
                    //todo 弹出popview
                    ivRobatPlus.setImageResource(R.drawable.circle_plus);
                } else if (!TextUtils.isEmpty(etRobat.getText().toString())) {
                    ivRobatPlus.setImageResource(R.drawable.send);
                    ivRobatPlus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            request();
                            RobatBean robatBean = new RobatBean(msg, RobatBean.SEND);
                            list.add(robatBean);
                            adapter.notifyDataSetChanged();
                            etRobat.setText("");
                        }
                    });
                }
            }
        });
        list = new ArrayList<>();
        adapter = new RobatAdapter(list, RobatActivity.this);
        lvRobat.setAdapter(adapter);
        //点击发送信息
      /*  ivRobatPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    //访问网络方法
    private void request() {
        HttpUtil httpUtil = new HttpUtil();
        Robat_imp request = httpUtil.getRequest(BaseUrl);
        msg = etRobat.getText().toString().trim();
        url = "openapi/api?key=d1525b710de1405380f7d554006bac36&info=" + msg;
        request.getRobat(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RobatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RobatBean value) {
                        RobatBean robatBean = new RobatBean(value.getText(), RobatBean.RECIVER);
                        list.add(robatBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick({R.id.iv_robat_cancel, R.id.iv_robat_emoji, R.id.iv_robat_plus, R.id.iv_robat_voice, R.id.iv_robat_chat})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_robat_cancel:
                finish();
                break;
            case R.id.iv_robat_emoji:
                Toast.makeText(this, "emoji", Toast.LENGTH_SHORT).show();
                //todo emoji表情未实现
                break;
            case R.id.iv_robat_plus:
                break;
            case R.id.iv_robat_voice:
                Toast.makeText(this, "voice", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_robat_chat:
                Toast.makeText(this, "右上角的小人", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
