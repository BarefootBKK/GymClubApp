package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.gymclubapp.R;
import com.example.gymclubapp.util.ActivityFunctionUtil;
import com.example.gymclubapp.controller.ActivityController;
import com.example.gymclubapp.controller.MyFragmentController;
import com.example.gymclubapp.controller.TabViewPagerController;
import com.example.gymclubapp.fragment.subFragment.CourseCoachSubFragment;
import com.example.gymclubapp.fragment.subFragment.CourseItemSubFragment;
import com.example.gymclubapp.fragment.subFragment.TrainingDietSubFragment;
import com.example.gymclubapp.fragment.subFragment.TrainingFitnessSubFragment;
import com.example.gymclubapp.fragment.subFragment.TrainingRunSubFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private final int defaultFragmentIndex = 0;
    private final int nullFragmentIndex = -1;
    private int currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!ActivityFunctionUtil.getIsLogin()) {
            // 若是第一次打开应用，进入登录界面
            startActivity(new Intent(this, SignInActivity.class));
            ActivityController.finishActivity(this);
        } else {
            setContentView(R.layout.activity_main);
            this.initFragment();
            this.initTabLayout();
            initItem();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        MyFragmentController.addFragment(getSupportFragmentManager().findFragmentById(R.id.trainingFragment));
        MyFragmentController.addFragment(getSupportFragmentManager().findFragmentById(R.id.courseFragment));
        MyFragmentController.addFragment(getSupportFragmentManager().findFragmentById(R.id.userFragment));
        this.currentFragment = nullFragmentIndex;
        showFragment(defaultFragmentIndex, false);
    }

    /**
     * 显示指定fragment
     * @param fragmentIndex
     */
    public void showFragment(int fragmentIndex, boolean isForcedReplace) {

        if (currentFragment != fragmentIndex || !isForcedReplace) {
            List<Fragment> fragmentList = MyFragmentController.getFragmentList();
            // 隐藏前一fragment
            for (Fragment fragment : fragmentList) {
                getSupportFragmentManager().beginTransaction().hide(fragment).commit();
            }
            getSupportFragmentManager().beginTransaction().show(fragmentList.get(fragmentIndex)).commit();
            currentFragment = fragmentIndex;
        }
    }

    /**
     * 初始化tabLayout
     */
    private void initTabLayout() {
        TabViewPagerController tabViewPagerController = new TabViewPagerController();
        // 课程栏
        tabViewPagerController.makeNewAdapter(getSupportFragmentManager(),
                (TabLayout) findViewById(R.id.tabLayoutCourse),
                (ViewPager) findViewById(R.id.viewPagerCourse));
        tabViewPagerController.addTab("课程", "教练");
        tabViewPagerController.addFragment(new CourseItemSubFragment(), new CourseCoachSubFragment());
        tabViewPagerController.setup();
        // 训练栏
        tabViewPagerController.makeNewAdapter(getSupportFragmentManager(),
                (TabLayout) findViewById(R.id.tabLayoutTraining),
                (ViewPager) findViewById(R.id.viewPagerTraining));
        tabViewPagerController.addTab("饮食", "健身", "跑步");
        tabViewPagerController.addFragment(new TrainingDietSubFragment(),
                                            new TrainingFitnessSubFragment(),
                                            new TrainingRunSubFragment());
        tabViewPagerController.setup();
    }

    private void initItem() {

    }
}
