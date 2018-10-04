package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.WindowManager;
import com.example.gymclubapp.R;
import com.example.gymclubapp.Util.ActivityFunctionUtil;
import com.example.gymclubapp.controller.ActivityController;
import com.example.gymclubapp.controller.MyFragmentController;

import java.util.List;

public class MainActivity extends BaseActivity {

    private int currentFragment = -1;
    Fragment[] fragments = new Fragment[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ActivityFunctionUtil.getIsLogin()) {
            // 若是第一次打开应用，进入登录界面
            startActivity(new Intent(this, SignInActivity.class));
            ActivityController.finishActivity(this);

        } else {
            setContentView(R.layout.activity_main);
            this.initFragment();
        }
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        MyFragmentController.addFragment(getSupportFragmentManager().findFragmentById(R.id.trainingFragment));
        MyFragmentController.addFragment(getSupportFragmentManager().findFragmentById(R.id.courseFragment));
        showFragment(0);
    }

    /**
     * 显示指定fragment
     * @param fragmentIndex
     */
    public void showFragment(int fragmentIndex) {

        if (currentFragment != fragmentIndex) {
            List<Fragment> fragmentList = MyFragmentController.getFragmentList();
            // 隐藏前一fragment
            for (Fragment fragment : fragmentList) {
                getSupportFragmentManager().beginTransaction().hide(fragment).commit();
            }
            getSupportFragmentManager().beginTransaction().show(fragmentList.get(fragmentIndex)).commit();
            currentFragment = fragmentIndex;
        }
    }
}
