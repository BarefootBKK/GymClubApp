package com.example.gymclubapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymclubapp.R;
import com.example.gymclubapp.activity.SignInActivity;
import com.example.gymclubapp.activity.TrainingRecordActivity;
import com.example.gymclubapp.config.CacheConfig;
import com.example.gymclubapp.util.ActivityFunctionUtil;
import com.example.gymclubapp.activity.MainActivity;
import com.example.gymclubapp.activity.TempActivity;

public class BaseFragment extends Fragment implements View.OnClickListener {
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

    /**
     * 初始化toolbar的选项菜单
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d("CourseContentFragment", "onCreateOptionsMenu: ");
        menu.clear();
        // 加载图标
        inflater.inflate(menuId, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.userCardViewTrainingData:
                ActivityFunctionUtil.toStartActivity(mainActivity, TrainingRecordActivity.class,
                        R.drawable.ic_data, "训练记录");
                break;
            case R.id.userCardViewCollection:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class,
                        R.drawable.ic_my_collection, "我的收藏");
                break;
            case R.id.userCardViewFeeling:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class,
                        R.drawable.ic_my_feeling, "我的心得");
                break;
            case R.id.userCardViewDynamic:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class,
                        R.drawable.ic_news, "我的动态");
                break;
            case R.id.userCircleImageView:
                ActivityFunctionUtil.toStartActivity(mainActivity, TempActivity.class,
                        R.drawable.fruit, "我的头像");
                break;
            case R.id.userLogout:
                // 退出登录
                ActivityFunctionUtil.toStartActivity(mainActivity, SignInActivity.class,
                        -1, "");
                ActivityFunctionUtil.saveDataWithSPByBoolean(mainActivity,
                        CacheConfig.LOGIN_CACHE, CacheConfig.LOGIN_CACHE_KEY, false);
                mainActivity.finish();
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
        mainActivity.findViewById(R.id.userLogout).setOnClickListener(this);
    }
}
