package com.example.gymclubapp.activity;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.gymclubapp.R;

public class TrainingRecordActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_training_record);
        setActivityToolbar(R.id.toolbarMyTrainingRecord, true, true);
    }
}
