package com.example.gymclubapp.activity;

import android.os.Bundle;

import com.example.gymclubapp.R;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setActivityToolbar(R.id.searchToolbar, true, false);
    }
}
