package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.gymclubapp.R;
import com.example.gymclubapp.controller.MainFragmentManager;
import com.example.gymclubapp.controller.TabViewPagerController;
import com.example.gymclubapp.fragment.subFragment.CourseCoachSubFragment;
import com.example.gymclubapp.fragment.subFragment.CourseItemSubFragment;
import com.example.gymclubapp.fragment.subFragment.TrainingDietSubFragment;
import com.example.gymclubapp.fragment.subFragment.TrainingFitnessSubFragment;
import com.example.gymclubapp.fragment.subFragment.TrainingRunSubFragment;

public class MainActivity extends BaseActivity {

    private MainFragmentManager mainFragmentManager;
    private final int defaultFragmentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initFragment();
        this.initTabLayout();

        /**if (!ActivityFunctionUtil.getIsLogin()) {
            // 若是第一次打开应用，进入登录界面
            startActivity(new Intent(this, SignInActivity.class));
            ActivityController.finishActivity(this);
        } else {
        }**/
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        mainFragmentManager = new MainFragmentManager(this);
        mainFragmentManager.addFragment(getSupportFragmentManager().findFragmentById(R.id.trainingFragment));
        mainFragmentManager.addFragment(getSupportFragmentManager().findFragmentById(R.id.courseFragment));
        mainFragmentManager.addFragment(getSupportFragmentManager().findFragmentById(R.id.userFragment));
        mainFragmentManager.showFragment(defaultFragmentIndex, false);
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

    /**
     * 获取已实例化的mainFragmentManager
     * @return
     */
    public MainFragmentManager getMainFragmentManager() {
        return this.mainFragmentManager;
    }
}
