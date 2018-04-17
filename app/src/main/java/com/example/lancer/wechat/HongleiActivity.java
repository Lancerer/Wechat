package com.example.lancer.wechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HongleiActivity extends AppCompatActivity {

    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.rela)
    RelativeLayout rela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honglei);
        ButterKnife.bind(this);
        String names = getIntent().getStringExtra("tohonglei");
        textView8.setText(names);
        if (names.equals("善阳")) {
            rela.setBackgroundResource(R.drawable.shanyang);
            textView8.setTextColor(0x000000);
        } else if (names.equals("洪磊")) {
            rela.setBackgroundResource(R.drawable.honglei);
        } else if (names.equals("徐林波")) {
            rela.setBackgroundResource(R.drawable.xulinbo);
        } else if (names.equals("狗杰")) {
            rela.setBackgroundResource(R.drawable.goujie);
        } else if (names.equals("谢月奎")) {
            rela.setBackgroundResource(R.drawable.xieyuekui);
        } else if (names.equals("王境泽")) {
            rela.setBackgroundResource(R.drawable.wangjingze);
        } else if (names.equals("王盛梁")) {
            rela.setBackgroundResource(R.drawable.wangshenglaing);
        }else if(names.equals("付徐信")){
            rela.setBackgroundResource(R.drawable.fuxuxin);
        }
    }
}
