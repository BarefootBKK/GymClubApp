package com.example.gymclubapp.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.example.gymclubapp.R;

public class ActivityFunctionUtil {

    private static boolean isLogin = false;  // 是否已登录

    public static ActivityFunctionUtil getInstance() {
        return new ActivityFunctionUtil();
    }

    /**
     * 获取应用状态
     * @return
     */
    public static boolean getIsLogin() {
        return isLogin;
    }

    /**
     * 设置应用状态
     * @param isLogin
     */
    public static void setIsLogin(boolean isLogin) {
        ActivityFunctionUtil.isLogin = isLogin;
    }

    /**
     * 向TabLayout中添加label
     * @param tabLayout
     * @param labelList
     */
    public static void addLabelToTabLayout(TabLayout tabLayout, int focusIndex, String... labelList) {

        for (int i = 0; i < labelList.length; i++) {
            String label = labelList[i];
            if (i == focusIndex - 1)
                tabLayout.addTab(tabLayout.newTab().setText(label), true);
            else
                tabLayout.addTab(tabLayout.newTab().setText(label), false);
        }
    }

    /**
     * 设置为沉浸式状态栏
     */
    public void setStatusBar(Activity activity) {

        boolean useThemeStatusBarColor = false;
        boolean useStatusBarColor = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemeStatusBarColor) {
                activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, R.color.colorAccent));//设置状态栏背景色
            } else {
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            Toast.makeText(activity, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * 跳转至目标Class
     * @param targetClass
     * @param data
     * @param resId
     */
    public static void toStartActivity(Activity activity, Class targetClass, int resId, String... dataArray) {

        Intent intent = new Intent(activity, targetClass);
        intent.putExtra("extra_data", dataArray);
        intent.putExtra("extra_res", resId);
        activity.startActivity(intent);
    }
}
