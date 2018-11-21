package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.gymclubapp.R;
import com.example.gymclubapp.config.HttpConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.NetworkTask;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.util.HttpUtil;
import com.example.gymclubapp.util.ToastUtil;

import java.io.IOException;

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
                ToastUtil.initToast(LaunchActivity.this);
                ServerConfig.initServerConfig(getResources().openRawResource(R.raw.ip));
                testConnection(0);
                startActivity(new Intent(LaunchActivity.this, SignInActivity.class));
                LaunchActivity.this.finish();
            }
        }, time);
    }

    private void testConnection(final int ip_position) {
        String ip = ServerConfig.getIpFromList(ip_position);
        if (ip.isEmpty()) {
            ToastUtil.showToast(LaunchActivity.this, "无法连接服务器！");
            return;
        } else {
            ServerConfig.updateIP(ip_position);
            NetworkTask networkTask = new NetworkTask(ServerConfig.getAddress("/login"), HttpConfig.GET, new HttpListener() {
                @Override
                public void onMessage(String jsonData) {

                }
                @Override
                public void onSuccess() {
                    return;
                }
                @Override
                public void onFailure(int failure_code, String failure_data) {
                    if (failure_code == 101) {
                        testConnection(ip_position + 1);
                    }
                }
            });
            networkTask.execute();
        }
    }
}
