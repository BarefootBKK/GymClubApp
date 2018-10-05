package com.example.gymclubapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymclubapp.R;

public class UserInfoFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.toolbarId = R.id.toolbarUser;
        super.menuId = R.menu.toolbar_user;
        super.onActivityCreated(savedInstanceState);
    }
}
