package com.example.gymclubapp.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.*;
import com.example.gymclubapp.R;
import com.example.gymclubapp.entity.FragmentAdapter;
import com.example.gymclubapp.fragment.subFragment.CourseItemSubFragment;

import java.util.ArrayList;
import java.util.List;

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
