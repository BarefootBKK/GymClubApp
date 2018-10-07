package com.example.gymclubapp.entity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private List<Fragment> fragmentList;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    /**
     * 设置fragmentList和titles
     * @param fragmentList
     * @param titles
     */
    public void setAdapterBasicList(List<Fragment> fragmentList, String... titles) {
        this.fragmentList = fragmentList;
        this.titles = titles;
    }
}
