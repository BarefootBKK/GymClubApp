package com.example.gymclubapp.activity;

import android.os.Bundle;
import com.example.gymclubapp.R;
import com.example.gymclubapp.controller.ActivityController;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityController.finishActivity(ActivityController.activity_signIn);
    }

}
