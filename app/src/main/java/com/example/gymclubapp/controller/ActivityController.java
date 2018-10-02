package com.example.gymclubapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.example.gymclubapp.R;
import com.example.gymclubapp.activity.MainActivity;
import com.example.gymclubapp.activity.SignInActivity;

public class ActivityController {

    private static boolean isFirst = true;

    public static void finishActivity(Activity activity) {
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }

    public static boolean getIsFirst() {
        return isFirst;
    }

    public static void setIsFirst(boolean isFirst) {
        ActivityController.isFirst = isFirst;
    }

    public static ActivityController getObject() {
        return new ActivityController();
    }

    /**
     * 沉浸式状态栏
     */
    public void setStatusBar(Activity activity) {

        boolean useThemestatusBarColor = false;
        boolean useStatusBarColor = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
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
     * 设置listener
     * @param activity
     * @param button
     * @param targetClass
     * @param isFinishCurrentActivity
     */
    public static void setButtonListener(Button button, final Activity activity,
                                         final Class targetClass, final boolean isFinishCurrentActivity) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断是否进入主页
                if (targetClass.equals(MainActivity.class)) {
                    ActivityController.setIsFirst(false);
                }
                Intent intent = new Intent(activity, targetClass);
                activity.startActivity(intent);
                // 是否结束当前活动
                if (isFinishCurrentActivity) {
                    ActivityController.finishActivity(activity);
                }
            }
        });
    }
}
