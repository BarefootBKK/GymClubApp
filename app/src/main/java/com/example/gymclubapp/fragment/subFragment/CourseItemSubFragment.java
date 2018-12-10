package com.example.gymclubapp.fragment.subFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymclubapp.R;
import com.example.gymclubapp.adapters.CourseAdapter;
import com.example.gymclubapp.config.BasicConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.Course;
import com.example.gymclubapp.entity.DatabaseRunnable;
import com.example.gymclubapp.fragment.CourseContentFragment;
import com.example.gymclubapp.util.HttpUtil;

import org.litepal.LitePal;

import java.util.List;

public class CourseItemSubFragment extends CourseContentFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.course_recyclerview, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initTrainingListData(R.id.course_ry, R.id.course_swipeContainer, R.id.course_swipe_error_msg);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSuccess(String jsonData) {
        // 获取解析数据
        List<Course> courseList = parseCourseWithJSON(jsonData);
        // 更新列表
        super.updateTrainingListData(new CourseAdapter(courseList, R.layout.course_item, getActivity()));
        // 保存缓存信息
        super.saveCacheData(new DatabaseRunnable<>(courseList, BasicConfig.CLASS_COURSE));
    }

    @Override
    protected void loadData() {
        if (HttpUtil.isNetConnect(getActivity())) {
            startNetworkTaskByGET(ServerConfig.getAddress("/course"));
        } else {
            // 读取缓存
            List<Course> courseList = LitePal.findAll(Course.class);
            RecyclerView.Adapter adapter = new CourseAdapter(courseList, R.layout.course_item, getActivity());
            loadCache(adapter);
        }
    }
}
