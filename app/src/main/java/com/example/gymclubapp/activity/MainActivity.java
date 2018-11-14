package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.gymclubapp.R;
import com.example.gymclubapp.controller.MyFragmentController;
import com.example.gymclubapp.controller.TabViewPagerController;
import com.example.gymclubapp.fragment.subFragment.CoachItemSubFragment;
import com.example.gymclubapp.fragment.subFragment.CourseItemSubFragment;
import com.example.gymclubapp.fragment.subFragment.CourseItemSubFragment;
import com.example.gymclubapp.fragment.subFragment.TrainingDietSubFragment;
import com.example.gymclubapp.fragment.subFragment.TrainingFitnessSubFragment;
import com.example.gymclubapp.fragment.subFragment.TrainingRunSubFragment;

public class MainActivity extends BaseActivity {

    private MyFragmentController myFragmentController;
    private final int defaultFragmentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initFragment();
        this.initTabLayout();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        myFragmentController = new MyFragmentController(this);
        myFragmentController.addFragment(getSupportFragmentManager().findFragmentById(R.id.trainingFragment));
        myFragmentController.addFragment(getSupportFragmentManager().findFragmentById(R.id.courseFragment));
        myFragmentController.addFragment(getSupportFragmentManager().findFragmentById(R.id.userFragment));
        myFragmentController.showFragment(defaultFragmentIndex, false);
    }

    /**
     * 初始化tabLayout
     */
    private void initTabLayout() {
        TabViewPagerController tabViewPagerController = new TabViewPagerController();
        /**
         * 课程栏
         * 1.添加tab
         * 2.添加fragment
         * 3.建立
         */
        tabViewPagerController.makeNewAdapter(getSupportFragmentManager(),
                (TabLayout) findViewById(R.id.tabLayoutCourse),
                (ViewPager) findViewById(R.id.viewPagerCourse));
        tabViewPagerController.addTab("课程", "教练");
        tabViewPagerController.addFragment(new CourseItemSubFragment(), new CoachItemSubFragment());
        tabViewPagerController.setup();
        /**
         * 训练栏
         * 1.添加tab
         * 2.添加fragment
         * 3.建立
         */
        tabViewPagerController.makeNewAdapter(getSupportFragmentManager(),
                (TabLayout) findViewById(R.id.tabLayoutTraining),
                (ViewPager) findViewById(R.id.viewPagerTraining));
        tabViewPagerController.addTab("饮食", "健身", "跑步");
        tabViewPagerController.addFragment(new TrainingDietSubFragment(),
                                            new TrainingFitnessSubFragment(),
                                            new TrainingRunSubFragment());
        tabViewPagerController.setup();
    }

    /**
     * 获取已实例化的mainFragmentManager
     * @return
     */
    public MyFragmentController getMyFragmentController() {
        return this.myFragmentController;
    }
}
