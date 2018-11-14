package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gymclubapp.R;
import com.example.gymclubapp.util.ToastUtil;

public class CoachDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_details);
        setActivityToolbar(R.id.coachDetail_toolbar, true, false);

        Button button_join = findViewById(R.id.coachDetail_join);
        button_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(CoachDetailActivity.this, "报名成功！");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar_coach_details, menu);
        return true;
    }
}
