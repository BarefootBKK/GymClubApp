package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.gymclubapp.R;
import com.example.gymclubapp.controller.ActivityController;

public class MainActivity extends BaseActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_training:
                    mTextMessage.setText(R.string.title_training);
                    return true;
                case R.id.navigation_course:
                    mTextMessage.setText(R.string.title_course);
                    return true;
                case R.id.navigation_user:
                    mTextMessage.setText(R.string.title_user);
                    return true;
            }
            return false;
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ActivityController.getIsLogin()) {
            // 若是第一次打开应用，进入登录界面
            startActivity(new Intent(this, SignInActivity.class));
            ActivityController.finishActivity(this);

        } else {
            setContentView(R.layout.activity_main);
            setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

            mTextMessage = (TextView) findViewById(R.id.toolbar_title);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }
    }

}
