package com.example.lancer.wechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
     /*   LinearLayout linearLayout = findViewById(R.id.ll);
        linearLayout.setBackgroundResource(R.drawable.welcome);*/
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(splashActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}
