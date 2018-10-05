package com.example.gymclubapp.fragment.subFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymclubapp.R;
import com.example.gymclubapp.Util.ActivityFunctionUtil;
import com.example.gymclubapp.activity.CourseDetailActivity;
import com.example.gymclubapp.activity.TempActivity;

public class CourseItemSubFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_item, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initListener();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 初始化监听器
     */
    private void initListener() {
        (getActivity()).findViewById(R.id.course_cardView_1).setOnClickListener(this);
        (getActivity()).findViewById(R.id.course_cardView_2).setOnClickListener(this);
        (getActivity()).findViewById(R.id.course_cardView_3).setOnClickListener(this);
        (getActivity()).findViewById(R.id.course_cardView_4).setOnClickListener(this);
        (getActivity()).findViewById(R.id.course_cardView_5).setOnClickListener(this);
        (getActivity()).findViewById(R.id.course_cardView_6).setOnClickListener(this);
        (getActivity()).findViewById(R.id.course_cardView_7).setOnClickListener(this);
        (getActivity()).findViewById(R.id.course_cardView_8).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.course_cardView_1:
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class, "瑜伽塑身", R.drawable.yoga);
                break;
            case R.id.course_cardView_2:
                ActivityFunctionUtil.toStartActivity(getActivity(), TempActivity.class, "腹肌训练", R.drawable.abdominal_muscle);
                break;
            case R.id.course_cardView_3:
                ActivityFunctionUtil.toStartActivity(getActivity(), TempActivity.class, "跑步热身", R.drawable.run);
                break;
            case R.id.course_cardView_4:
                ActivityFunctionUtil.toStartActivity(getActivity(), TempActivity.class, "极速燃脂", R.drawable.fat_reduction);
                break;
            case R.id.course_cardView_5:
                ActivityFunctionUtil.toStartActivity(getActivity(), TempActivity.class, "增肌专项", R.drawable.muscle_augmentation);
                break;
            case R.id.course_cardView_6:
                ActivityFunctionUtil.toStartActivity(getActivity(), TempActivity.class, "臀腿训练", R.drawable.buttocks_legs);
                break;
            case R.id.course_cardView_7:
                ActivityFunctionUtil.toStartActivity(getActivity(), TempActivity.class, "上肢强化", R.drawable.upper_limb);
                break;
            case R.id.course_cardView_8:
                ActivityFunctionUtil.toStartActivity(getActivity(), TempActivity.class, "舒缓放松", R.drawable.relax);
                break;
            default:
                break;
        }
    }
}
