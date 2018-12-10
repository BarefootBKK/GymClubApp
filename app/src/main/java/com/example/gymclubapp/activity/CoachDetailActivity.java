package com.example.gymclubapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymclubapp.R;
import com.example.gymclubapp.config.BasicConfig;
import com.example.gymclubapp.entity.Coach;
import com.example.gymclubapp.util.ToastUtil;
import com.squareup.picasso.Picasso;

public class CoachDetailActivity extends BaseActivity implements View.OnClickListener{
    private ImageView head_img;
    private ImageView poster;
    private TextView coach_name;
    private TextView coach_title;
    private TextView coach_signature;
    private TextView coach_courseType;
    private TextView coach_haveCourse;
    private TextView coach_intro;
    private TextView coach_stuNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_details);
        setActivityToolbar(R.id.coachDetail_toolbar, true, false);
        // 初始化信息
        initInfo();
        // 获取控件
        Button button_join = findViewById(R.id.coachDetail_join);
        // 联系方式
        ImageView imageView_email = findViewById(R.id.coachDetail_mail);
        ImageView imageView_phone = findViewById(R.id.coachDetail_phone);
        ImageView imageView_sms = findViewById(R.id.coachDetail_sms);
        // 设置监听器
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
                startTargetActivity(Intent.ACTION_SENDTO, "mailto:16301151@bjtu.edu.cn");
                break;
            case R.id.coachDetail_phone:
                startTargetActivity(Intent.ACTION_DIAL, "tel:13161295977");
                break;
            case R.id.coachDetail_sms:
                startTargetActivity(Intent.ACTION_VIEW, "smsto:13161295977");
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

    private void initInfo() {
        Coach coach = (Coach) getIntent().getParcelableExtra(BasicConfig.INTENT_DATA_NAME);
        // 获取控件
        head_img = findViewById(R.id.coachDetail_headImage);
        poster = findViewById(R.id.coachDetail_poster);
        coach_name = findViewById(R.id.coachDetail_coachName);
        coach_title = findViewById(R.id.coachDetail_title);
        coach_signature = findViewById(R.id.coachDetail_signature);
        coach_courseType = findViewById(R.id.coachDetail_courseType);
        coach_haveCourse = findViewById(R.id.coachDetail_haveCourse);
        coach_intro = findViewById(R.id.coachDetail_intro);
        coach_stuNum = findViewById(R.id.coachDetail_studentNum);
        // 填入信息
        Picasso.get().load(coach.getCoachHeadImg()).into(head_img);
        Picasso.get().load(coach.getCoachHeadImg()).into(poster);
        coach_name.setText(coach.getCoachName());
        coach_title.setText(coach.getCoachTitle());
        coach_signature.setText(coach.getCoachSignature());
        coach_courseType.setText(coach.getCoachCourseType());
        coach_haveCourse.setText(coach.getCoachHaveCourse());
        coach_intro.setText(coach.getCoachIntro());
        coach_stuNum.setText(coach.getStudentNum());
    }
}
