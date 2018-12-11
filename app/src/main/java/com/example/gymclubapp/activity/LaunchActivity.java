package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.gymclubapp.R;
import com.example.gymclubapp.config.CacheConfig;
import com.example.gymclubapp.config.NetConfig;
import com.example.gymclubapp.entity.Profile;
import com.example.gymclubapp.util.ActivityFunctionUtil;
import com.example.gymclubapp.util.HttpUtil;
import com.example.gymclubapp.util.ToastUtil;

import org.litepal.LitePal;

import java.util.List;

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
                // HttpUtil.testConnection(LaunchActivity.this, null);
                // 检查网络连接
                NetConfig.CURRENT_NETWORK_TYPE = HttpUtil.getNetWorkState(LaunchActivity.this);
                if (NetConfig.CURRENT_NETWORK_TYPE < 0) {
                    ToastUtil.showToast(LaunchActivity.this, "网络未连接!");
                }
                // 判断是否已经登录
                Boolean isLogin = (Boolean) ActivityFunctionUtil.getDataWithSP(LaunchActivity.this,
                        CacheConfig.LOGIN_CACHE, CacheConfig.LOGIN_CACHE_KEY, CacheConfig.SP_BOOLEAN);
                if (isLogin) {
                    List<Profile> profileList = LitePal.findAll(Profile.class);
                    int profileCount = profileList.size();
                    if (profileCount > 0) {
                        ActivityFunctionUtil.toStartActivityByParcelable(
                                LaunchActivity.this, MainActivity.class, profileList.get(profileCount - 1));
                    } else {
                        startActivity(new Intent(LaunchActivity.this, SignInActivity.class));
                        ToastUtil.showToast(LaunchActivity.this, "登录信息已过时，请重新登录!");
                    }
                } else {
                    startActivity(new Intent(LaunchActivity.this, SignInActivity.class));
                }
                LaunchActivity.this.finish();
            }
        }, time);
    }
}
