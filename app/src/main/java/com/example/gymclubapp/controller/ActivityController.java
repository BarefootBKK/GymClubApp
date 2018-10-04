package com.example.gymclubapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.example.gymclubapp.R;
import com.example.gymclubapp.Util.ActivityFunctionUtil;
import com.example.gymclubapp.activity.MainActivity;
import com.example.gymclubapp.activity.SignInActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityController {

    private static List<Activity> activityList = new ArrayList<>();

    /**
     * 停止一个Activity
     * @param activity
     */
    public static void finishActivity(Activity activity) {
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 停止所有Activity
     */
    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 添加一个Activity
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * pop一个指定位置的ctivity
     * @param activityA
     * @param index
     */
    public static void popActivity(Activity activity, int index) {
        activityList.remove(index);
    }
}
