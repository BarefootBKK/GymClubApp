package com.example.gymclubapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gymclubapp.R;
import com.example.gymclubapp.util.ToastUtil;

public class CoachDetailActivity extends BaseActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_details);
        setActivityToolbar(R.id.coachDetail_toolbar, true, false);

        Button button_join = findViewById(R.id.coachDetail_join);
        ImageView imageView_email = findViewById(R.id.coachDetail_mail);
        ImageView imageView_phone = findViewById(R.id.coachDetail_phone);
        ImageView imageView_sms = findViewById(R.id.coachDetail_sms);
        button_join.setOnClickListener(this);
        imageView_email.setOnClickListener(this);
        imageView_phone.setOnClickListener(this);
        imageView_sms.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar_coach_details, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coachDetail_join:
                ToastUtil.showToast(CoachDetailActivity.this, "报名成功！");
                break;
            case R.id.coachDetail_mail:
                startTargetActivity(Intent.ACTION_SENDTO, "mailto:23333@bjtu.edu.cn");
                break;
            case R.id.coachDetail_phone:
                startTargetActivity(Intent.ACTION_DIAL, "tel:23333");
                break;
            case R.id.coachDetail_sms:
                startTargetActivity(Intent.ACTION_VIEW, "smsto:23333");
                break;
            default:
                break;
        }
    }

    private void startTargetActivity(String action, String str_uri) {
        Intent intent = new Intent(action);
        intent.setData(Uri.parse(str_uri));
        startActivity(intent);
    }
}
