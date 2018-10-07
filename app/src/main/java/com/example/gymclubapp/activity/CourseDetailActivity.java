package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymclubapp.R;
import com.example.gymclubapp.util.ActivityFunctionUtil;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_course_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 返回
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            // 相关教练
            case R.id.item_course_detail_relativeCoach:
                if (((TextView) findViewById(R.id.headindCourseDetails))
                        .getText().equals("瑜伽塑身")) {
                    ActivityFunctionUtil.toStartActivity(this, CoachDetailActivity.class, -1, "");
                } else {
                    Toast.makeText(this, "找不到欸", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        return true;
    }
}
