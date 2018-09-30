package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.gymclubapp.R;

public class MainActivity extends BaseActivity {

    private TextView mTextMessage;
    private int icons[] = {R.drawable.ic_training, R.drawable.ic_course, R.drawable.ic_mine,
                            R.drawable.ic_training_fill, R.drawable.ic_course_fill, R.drawable.ic_mine_fill};

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



    private void setItemIcon(MenuItem item, int icon_1, int icon_2, int icon_3) {
        item.setIcon(icons[icon_1]);
        item.setIcon(icons[icon_2]);
        item.setIcon(icons[icon_3]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
