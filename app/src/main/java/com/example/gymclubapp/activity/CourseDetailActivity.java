package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymclubapp.R;
import com.example.gymclubapp.adapters.VideoAdapter;
import com.example.gymclubapp.config.BasicConfig;
import com.example.gymclubapp.entity.Course;
import com.example.gymclubapp.util.ToastUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailActivity extends BaseActivity {
    private ImageView poster;
    private TextView courseTitle;
    private TextView trainingPart;
    private TextView courseIntro;

    int[] viewId = {R.id.posterCourseDetails,
                            R.id.courseNameCourseDetails,
                            R.id.bodyPositionCourseDetails,
                            R.id.courseContentCourseDetails};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        // 设置toolbar
        setActivityToolbar(R.id.toolbarCourseDetails, true, false);
        // 初始化数据
        initData();
        // 设置监听器
        Button buttonJoinCourse = findViewById(R.id.buttonJoinCourse);
        buttonJoinCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(CourseDetailActivity.this, "加入课程成功！");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_course_details, menu);
        return true;
    }

    private void initData() {
        Course course = getIntent().getParcelableExtra(BasicConfig.INTENT_DATA_NAME);
        poster = findViewById(R.id.posterCourseDetails);
        courseTitle = findViewById(R.id.courseNameCourseDetails);
        trainingPart = findViewById(R.id.bodyPositionCourseDetails);
        courseIntro = findViewById(R.id.courseContentCourseDetails);
        // 加载信息
        Picasso.get().load(course.getCoursePoster()).into(poster);
        courseTitle.setText(course.getCourseName());
        trainingPart.setText(course.getCourseTrainingPart());
        courseIntro.setText(course.getCourseIntro());
        // 教学视频
        List<Course> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(course);
        }
        VideoAdapter videoAdapter = new VideoAdapter(list, R.layout.video_item, this);
        RecyclerView recyclerView = findViewById(R.id.videoCy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(videoAdapter);
    }

}
