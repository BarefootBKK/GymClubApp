package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.gymclubapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TempActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        String data = getIntent().getStringExtra("extra_data");
        int resId = getIntent().getIntExtra("extra_res", -1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTemp);
        setSupportActionBar(toolbar);
        ((TextView) findViewById(R.id.tempContent)).setText("这是从【" + data + "】传来的数据.");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);      // 显示返回按钮
            actionBar.setTitle(data);
        }

        if (resId >= 0) {
            ((CircleImageView) findViewById(R.id.tempCircleImageView)).setImageResource(resId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                break;
        }
        return true;
    }
}
