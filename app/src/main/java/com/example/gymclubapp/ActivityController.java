package com.example.gymclubapp;

import android.app.Activity;

public class ActivityController {

    public static Activity activity_signIn;

    public static void finishActivity(Activity activity) {
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }
}
