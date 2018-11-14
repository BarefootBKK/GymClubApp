package com.example.gymclubapp.controller;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.gymclubapp.adapters.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabViewPagerController {

    private FragmentManager fragmentManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;
    private List<Fragment> fragmentList;
    private String[] titles;

    public void makeNewAdapter(FragmentManager fragmentManager, TabLayout tabLayout, ViewPager viewPager) {
        this.fragmentManager = fragmentManager;
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
        this.fragmentList = new ArrayList<>();

        viewPager.setOffscreenPageLimit(4);
    }

    public void addFragment(Fragment... fragments) {
        for (Fragment fragment : fragments) {
            fragmentList.add(fragment);
        }
    }

    public void addTab(String... titles) {
        this.titles = titles;
    }

    public void setup() {
        fragmentAdapter = new FragmentAdapter(fragmentManager);
        fragmentAdapter.setAdapterBasicList(fragmentList, titles);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
