package com.example.gymclubapp.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.support.v7.widget.Toolbar;
import com.example.gymclubapp.R;
import com.example.gymclubapp.Util.ActivityFunctionUtil;
import com.example.gymclubapp.activity.MainActivity;

/**
 * Training界面
 */
public class TrainingContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_training, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // 重载布局
        setHasOptionsMenu(true);
        // 加载toolbar
        MainActivity mainActivity = (MainActivity) getActivity();
        Toolbar toolbar = mainActivity.findViewById(R.id.toolbarTraining);
        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);    // 隐藏默认title
        // 为顶部导航栏添加label
        TabLayout tabLayoutTraining = mainActivity.findViewById(R.id.tabLayoutTraining);
        ActivityFunctionUtil.addLabelToTabLayout(tabLayoutTraining, 2, "饮食", "健身", "跑步");

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d("TrainingContentFragment", "onCreateOptionsMenu: ");
        menu.clear();
        // 加载图标
        inflater.inflate(R.menu.toolbar_training, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
