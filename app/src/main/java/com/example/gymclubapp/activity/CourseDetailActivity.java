package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymclubapp.R;

public class CourseDetailActivity extends BaseActivity {

    int[] viewId = {R.id.posterCourseDetails,
                            R.id.headindCourseDetails,
                            R.id.bodyPositionCourseDetails,
                            R.id.courseContentCourseDetails};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        String[] courseDetailArray = getIntent().getStringArrayExtra("extra_data");
        int coursePosterId = getIntent().getIntExtra("extra_res", -1);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbarCourseDetails));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);      // 显示返回按钮
            actionBar.setDisplayShowTitleEnabled(false);    // 隐藏标题
        }

        if (courseDetailArray.length > 0) {
            for (int i = 0; i < courseDetailArray.length; i++) {
                ((TextView) findViewById(viewId[i + 1])).setText(courseDetailArray[i]);
            }
        }

        if (coursePosterId >= 0) {
            ((ImageView) findViewById(viewId[0])).setImageResource(coursePosterId);
        }

        Button buttonJoinCourse = findViewById(R.id.buttonJoinCourse);
        buttonJoinCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CourseDetailActivity.this, "加入课程成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
