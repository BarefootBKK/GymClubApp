package com.example.gymclubapp.fragment;

import android.os.Bundle;
import android.view.*;
import com.example.gymclubapp.R;

public class CourseContentFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.toolbarId = R.id.toolbarCourse;
        super.menuId = R.menu.toolbar_course;
        super.onActivityCreated(savedInstanceState);
    }

}
