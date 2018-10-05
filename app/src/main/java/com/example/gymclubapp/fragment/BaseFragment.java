package com.example.gymclubapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymclubapp.R;
import com.example.gymclubapp.Util.ActivityFunctionUtil;
import com.example.gymclubapp.activity.MainActivity;
import com.example.gymclubapp.activity.SearchActivity;
import com.example.gymclubapp.activity.TempActivity;

public class BaseFragment extends Fragment implements View.OnClickListener{

    protected int toolbarId;
    protected int menuId;
    private MainActivity mainActivity;

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
        Toolbar toolbar = mainActivity.findViewById(toolbarId);
        mainActivity.setSupportActionBar(toolbar);
        toolbar.inflateMenu(menuId);
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);    // 隐藏默认title
        initClickListener();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d("CourseContentFragment", "onCreateOptionsMenu: ");
        menu.clear();
        // 加载图标
        inflater.inflate(menuId, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                ActivityFunctionUtil.toStartActivity(mainActivity, SearchActivity.class, "search", R.drawable.ic_search);
                break;
            case R.id.item_record:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class, "MyRecord", R.drawable.ic_my_records);
                break;
            case R.id.item_message:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class, "Message", R.drawable.ic_msg);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.userCardViewTrainingData:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class, "训练记录", R.drawable.ic_data);
                break;
            case R.id.userCardViewCollection:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class, "我的收藏", R.drawable.ic_my_collection);
                break;
            case R.id.userCardViewFeeling:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class, "我的心得", R.drawable.ic_my_feeling);
                break;
            case R.id.userCardViewDynamic:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class, "我的动态", R.drawable.ic_news);
                break;
            case R.id.userCircleImageView:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class, "我的头像", R.drawable.fruit);
                break;
            default:
                break;
        }
    }

    /**
     * 设置监听器
     */
    private void initClickListener() {
        mainActivity.findViewById(R.id.userCardViewTrainingData).setOnClickListener(this);
        mainActivity.findViewById(R.id.userCardViewCollection).setOnClickListener(this);
        mainActivity.findViewById(R.id.userCardViewFeeling).setOnClickListener(this);
        mainActivity.findViewById(R.id.userCardViewDynamic).setOnClickListener(this);
        mainActivity.findViewById(R.id.userCircleImageView).setOnClickListener(this);
    }
}
