package com.example.gymclubapp.controller;

import android.support.v4.app.Fragment;

import com.example.gymclubapp.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentController {

    private List<Fragment> fragmentList;
    private MainActivity mainActivity;
    private int currentFragment = -1;

    /**
     * 构造函数1
     * @param mainActivity
     */
    public MyFragmentController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.fragmentList = new ArrayList<>();
    }

    /**
     * 构造函数2
     * @param mainActivity
     * @param fragmentList
     */
    public MyFragmentController(MainActivity mainActivity, List<Fragment> fragmentList) {
        this.mainActivity = mainActivity;
        this.fragmentList = fragmentList;
    }

    /**
     * 添加一个fragment
     * @param fragment
     */
    public void addFragment(Fragment fragment) {
        this.fragmentList.add(fragment);
    }

    /**
     * 获取fragment
     * @param index
     * @return fragment
     */
    public Fragment getFragment(int index) {
        return fragmentList.get(index);
    }

    /**
     * 获取fragmentList
     * @return fragmentList
     */
    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    /**
     * 清空List
     */
    public void clearAllFragmentFromList() {
        fragmentList.clear();
    }


    /**
     * 显示指定fragment
     * @param fragmentIndex
     */
    public void showFragment(int fragmentIndex, boolean isForcedReplace) {
        // 若当前fragment不重复或未强制替换
        if (currentFragment != fragmentIndex || isForcedReplace) {
            // 先隐藏，后显示
            for (Fragment fragment : fragmentList) {
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .hide(fragment).commit();
            }
            mainActivity.getSupportFragmentManager().beginTransaction()
                    .show(fragmentList.get(fragmentIndex)).commit();
        }
    }

}
