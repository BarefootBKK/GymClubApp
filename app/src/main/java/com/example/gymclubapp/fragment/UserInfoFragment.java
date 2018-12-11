package com.example.gymclubapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymclubapp.R;
import com.example.gymclubapp.activity.MainActivity;
import com.example.gymclubapp.entity.Profile;
import com.squareup.picasso.Picasso;

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
        loadUserProfile();
        super.onActivityCreated(savedInstanceState);
    }

    private void loadUserProfile() {
        Profile profile = MainActivity.profile;
        TextView textView_nickname = getActivity().findViewById(R.id.userCardViewNickname);
        ImageView userImageView = getActivity().findViewById(R.id.userCircleImageView);
        textView_nickname.setText(profile.getNickname());
        if (profile.getHeadImageUrl() != null) {
            Picasso.get().load(profile.getHeadImageUrl()).into(userImageView);
        }
    }
}
