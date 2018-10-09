package com.example.gymclubapp.fragment.subFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymclubapp.R;
import com.example.gymclubapp.entity.CourseContent;
import com.example.gymclubapp.util.ActivityFunctionUtil;
import com.example.gymclubapp.activity.CourseDetailActivity;
import com.example.gymclubapp.util.ReadFromFileUtil;

public class CourseItemSubFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_list, container, false);
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

        CourseContent courseContent;

        switch (v.getId()) {
            // 瑜伽塑身
            case R.id.course_cardView_1:
                courseContent = ReadFromFileUtil.getCourseContent(getResources()
                        .openRawResource(R.raw.course_yoga));
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class,
                        R.drawable.yoga, courseContent.toArray());
                break;
            // 腹肌训练
            case R.id.course_cardView_2:
                courseContent = ReadFromFileUtil.getCourseContent(getResources()
                        .openRawResource(R.raw.course_abdominal_muscle_training));
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class,
                        R.drawable.abdominal_muscle, courseContent.toArray());
                break;
            // 跑步热身
            case R.id.course_cardView_3:
                courseContent = ReadFromFileUtil.getCourseContent(getResources()
                        .openRawResource(R.raw.course_runing));
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class,
                        R.drawable.run, courseContent.toArray());
                break;
            // 极速燃脂
            case R.id.course_cardView_4:
                courseContent = ReadFromFileUtil.getCourseContent(getResources()
                        .openRawResource(R.raw.course_fat_reduction));
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class,
                        R.drawable.fat_reduction, courseContent.toArray());
                break;
            // 增肌专项
            case R.id.course_cardView_5:
                courseContent = ReadFromFileUtil.getCourseContent(getResources()
                        .openRawResource(R.raw.course_muscle_agumentation));
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class,
                        R.drawable.muscle_augmentation, courseContent.toArray());
                break;
            // 臀腿训练
            case R.id.course_cardView_6:
                courseContent = ReadFromFileUtil.getCourseContent(getResources()
                        .openRawResource(R.raw.course_buttocks_legs));
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class, R.drawable.buttocks_legs, courseContent.toArray());
                break;
            // 上肢强化
            case R.id.course_cardView_7:
                courseContent = ReadFromFileUtil.getCourseContent(getResources()
                        .openRawResource(R.raw.course_upper_limb));
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class,
                        R.drawable.upper_limb, courseContent.toArray());
                break;
            // 舒缓放松
            case R.id.course_cardView_8:
                courseContent = ReadFromFileUtil.getCourseContent(getResources()
                        .openRawResource(R.raw.course_relax));
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class,
                        R.drawable.relax, courseContent.toArray());
                break;
            // break
            default:
                break;
        }
    }
}
