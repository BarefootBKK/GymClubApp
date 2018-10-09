package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.gymclubapp.R;

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
                startActivity(new Intent(LaunchActivity.this, SignInActivity.class));
                LaunchActivity.this.finish();
            }
        }, time);

    }
}
