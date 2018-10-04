package com.example.gymclubapp.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.*;
import android.support.v7.widget.Toolbar;
import com.example.gymclubapp.R;
import com.example.gymclubapp.activity.MainActivity;

public class BottomNavigationFragment extends Fragment {

    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottomnavigation, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        BottomNavigationView navigation = (BottomNavigationView) mainActivity.findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 按钮监听器
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_training:
                    mainActivity.showFragment(0);
                    return true;
                case R.id.navigation_course:
                    mainActivity.showFragment(1);
                    return true;
                case R.id.navigation_user:
                    return true;
            }
            return false;
        }

    };
}
