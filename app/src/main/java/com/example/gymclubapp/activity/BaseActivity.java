package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.gymclubapp.util.ActivityFunctionUtil;

public class BaseActivity extends AppCompatActivity {

    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFunctionUtil.getInstance().setStatusBar(this);  // 设置为沉浸式状态栏
        this.TAG = "类-" + this.getLocalClassName();                   // 打印日志
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + TAG);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: " + TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + TAG);
    }
}
