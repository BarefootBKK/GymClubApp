package com.example.gymclubapp.fragment;

import android.os.Bundle;
import android.view.*;
import com.example.gymclubapp.R;

/**
 * Training界面
 */
public class TrainingContentFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_training, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.toolbarId = R.id.toolbarTraining;
        super.menuId = R.menu.toolbar_training;
        super.onActivityCreated(savedInstanceState);
    }

}
