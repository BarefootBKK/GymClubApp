package com.example.gymclubapp;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityController.finishActivity(ActivityController.activity_signIn);
    }

}
