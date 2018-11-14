package com.example.gymclubapp.fragment.subFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.example.gymclubapp.R;
import com.example.gymclubapp.adapters.CourseAdapter;
import com.example.gymclubapp.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseItemSubFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.course_recyclerview, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Course course = new Course("瑜伽塑身", R.drawable.yoga);
            courseList.add(course);
        }
        CourseAdapter.activity = getActivity();
        CourseAdapter courseAdapter = new CourseAdapter(courseList, R.layout.course_item, getActivity());
        RecyclerView recyclerView = getActivity().findViewById(R.id.course_ry);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(courseAdapter);
        super.onActivityCreated(savedInstanceState);
    }
}
