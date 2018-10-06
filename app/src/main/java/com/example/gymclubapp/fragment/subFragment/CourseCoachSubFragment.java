package com.example.gymclubapp.fragment.subFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymclubapp.R;
import com.example.gymclubapp.activity.CoachDetailActivity;
import com.example.gymclubapp.controller.MyFragmentController;
import com.example.gymclubapp.util.ActivityFunctionUtil;

public class CourseCoachSubFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coach_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.id.coach_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityFunctionUtil.toStartActivity(getActivity(), CoachDetailActivity.class, -1, "");
            }
        });
//        Course course_1 = new Course("瑜伽", R.drawable.abdominal_muscle);
//        Course course_2 = new Course("跑步", R.drawable.run);
//
//        List<Course> courseList = new ArrayList<>();
//        courseList.add(course_1);
//        courseList.add(course_2);
//        courseList.add(course_1);
//        courseList.add(course_2);
//
//        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((MainActivity)getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(new CourseAdapter(courseList));
    }
}
