package com.example.lancer.wechat;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Slide;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    private EditText et;
    private ImageView cancel;
    private ImageView iverror;
    private Drawable drawableleft;
    private Drawable drawableright;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().setEnterTransition(new Slide().setDuration(300));
        getWindow().setExitTransition(new Slide().setDuration(300));
        et = findViewById(R.id.et);
        cancel = findViewById(R.id.cancel);
        iverror = findViewById(R.id.iv_error);
        //设置Edittext左侧,右侧的图片大小
        drawableleft = getResources().getDrawable(R.drawable.search);
        //drawableright = getResources().getDrawable(R.drawable.mg);
        drawableleft.setBounds(0, 0, 100, 100);
        // drawableright.setBounds(0, 0, 100, 100);
        et.setCompoundDrawables(drawableleft, null, null, null);
        //点击cancel键返回上一个activity
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //监听Edittext的内容变化
        et.addTextChangedListener(new TextWatcher() {
            // 输入前的监听
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("输入前确认执行该方法", "开始输入");
            }

            //监听输入内容变化
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("输入过程中执行该方法", "文字变化");
            }

            //输入完成后的监听
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(et.getText())) {
                    et.setCompoundDrawables(drawableleft, null, null, null);
                    iverror.setVisibility(View.VISIBLE);
                    iverror.setImageResource(R.drawable.err);
                } else if (TextUtils.isEmpty(et.getText())) {
                    et.setCompoundDrawables(drawableleft, null, drawableright, null);
                    iverror.setImageResource(R.drawable.mg);
                    iverror.setVisibility(View.VISIBLE);
                }

            }
        });
        //图片的点击事件，点击清空et内容
        iverror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
                iverror.setImageResource(R.drawable.mg);
                iverror.setVisibility(View.VISIBLE);
                et.setCompoundDrawables(drawableleft, null, null, null);
            }
        });
    }
}
