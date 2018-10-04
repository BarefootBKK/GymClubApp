package com.example.gymclubapp.controller;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentController {

    private static List<Fragment> fragmentList = new ArrayList<>();

    /**
     * 添加一个fragment
     * @param fragment
     */
    public static void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }

    /**
     * 获取fragment
     * @param index
     * @return fragment
     */
    public static Fragment getFragment(int index) {
        return fragmentList.get(index);
    }

    /**
     * 获取fragmentList
     * @return fragmentList
     */
    public static List<Fragment> getFragmentList() {
        return fragmentList;
    }
}
