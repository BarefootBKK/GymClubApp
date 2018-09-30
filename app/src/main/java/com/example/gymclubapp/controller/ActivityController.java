package com.example.gymclubapp.controller;

import android.app.Activity;

public class ActivityController {

    public static void finishActivity(Activity activity) {
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }
}
