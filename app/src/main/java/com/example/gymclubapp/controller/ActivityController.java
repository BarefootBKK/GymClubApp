package com.example.gymclubapp.controller;

import android.app.Activity;

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
