package com.example.gymclubapp.activity;

import android.os.Bundle;

import com.example.gymclubapp.R;

public class TrainingRecordActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_training_record);
        setActivityToolbar(R.id.toolbarMyTrainingRecord, true, true);
    }
}
