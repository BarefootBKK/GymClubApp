package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.gymclubapp.R;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.util.ToastUtil;

public class LaunchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Integer time = 1000;    //设置等待时间，单位为毫秒
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ServerConfig.ip = "192.168.2.170";
                ServerConfig.port = "8000";
                startActivity(new Intent(LaunchActivity.this, SignInActivity.class));
                ToastUtil.initToast(LaunchActivity.this);
                LaunchActivity.this.finish();
            }
        }, time);

    }
}
