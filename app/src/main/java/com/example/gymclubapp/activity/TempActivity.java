package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.gymclubapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TempActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        String[] data = getIntent().getStringArrayExtra("extra_data");
        int resId = getIntent().getIntExtra("extra_res", -1);

        ((Toolbar) findViewById(R.id.toolbarTemp)).setTitle(data[0]);
        setActivityToolbar(R.id.toolbarTemp, true, true);
        ((TextView) findViewById(R.id.tempContent)).setText("这是从【" + data[0] + "】传来的数据.");

        if (resId >= 0) {
            ((CircleImageView) findViewById(R.id.tempCircleImageView)).setImageResource(resId);
        }
    }
}
