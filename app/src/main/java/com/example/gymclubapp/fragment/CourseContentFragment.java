package com.example.gymclubapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import com.example.gymclubapp.R;
import com.example.gymclubapp.Util.ActivityFunctionUtil;
import com.example.gymclubapp.activity.MainActivity;
import com.example.gymclubapp.activity.SearchActivity;

public class CourseContentFragment extends Fragment {

    private  MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // 重载布局
        setHasOptionsMenu(true);
        // 加载toolbar
        mainActivity = (MainActivity) getActivity();
        Toolbar toolbar = mainActivity.findViewById(R.id.toolbarCourse);
        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);    // 隐藏默认title
        // 为顶部导航栏添加label
        TabLayout tabLayoutCourse = mainActivity.findViewById(R.id.tabLayoutCourse);
        ActivityFunctionUtil.addLabelToTabLayout(tabLayoutCourse, 1, "课程", "教练");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d("CourseContentFragment", "onCreateOptionsMenu: ");
        menu.clear();
        // 加载图标
        inflater.inflate(R.menu.toolbar_course, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                Intent intent = new Intent(mainActivity, SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
}
