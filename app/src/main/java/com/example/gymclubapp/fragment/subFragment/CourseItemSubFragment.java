package com.example.gymclubapp.fragment.subFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymclubapp.R;
import com.example.gymclubapp.util.ActivityFunctionUtil;
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

        String[] temp_content;
        String yoga = "瑜伽是起源于古印度的一种健身术，" +
                "是一种生活方式，一种生活理念，是一种指导生活" +
                "的思想体系又是一种博大精深的哲学体系。瑜伽在西方国家" +
                "近几十年从健身界炽热不衰至现在已融入他们的工作、生活、" +
                "像微软、苹果公司每周都要请专业瑜伽老师给员工们培训以便员" +
                "工能适时减压、放松。保养心身更好地有效地工作。我校自2007年" +
                "在全校范围内开设健身瑜伽公共选修课以来，深受广大学生的欢迎和喜爱。" +
                "随着健身瑜伽影响力的扩大和师资力量的增强，2009年在体育学院开设了" +
                "健身瑜伽选修课程。我们开设的瑜伽理论课主要内容有：瑜伽文化、瑜伽流派、" +
                "瑜伽生理学、瑜伽动作的创编及练习方法。术科主要内容有：基础瑜伽、瑜伽体位法、" +
                "呼吸、冥想、双人瑜伽。学生通过练习，能够掌握一种终身受用的健身方式，" +
                "克服生活中种种恶习，建立一套健康的生活方式。其次它不仅能强健肌肉骨骼而且" +
                "通过神经腺体的刺激，使整个身体心灵得到完全放松，有一个良好的人格气质。" +
                "通过本课程的学习与训练使学生在德、智、体、美等方面得到全面发展，具有社会公德、" +
                "社会责任感、义务感和履行职责的行为等基本素质；系统地掌握瑜伽的基本理论、" +
                "基本知识和基本技能，具备瑜伽教学、训练、裁判、设计、策划的基本能力，" +
                "提高学生对瑜伽运动的组织开展、推广普及和咨询指导能力，提高学生对瑜伽的探究心理。";

        switch (v.getId()) {
            case R.id.course_cardView_1:
                temp_content = new String[]{"瑜伽塑身", "全身", yoga};
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class, R.drawable.yoga, temp_content);
                break;
            case R.id.course_cardView_2:
                temp_content = new String[]{"腹肌训练", "腹部", "这是腹肌训练课程"};
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class, R.drawable.abdominal_muscle, temp_content);
                break;
            case R.id.course_cardView_3:
                temp_content = new String[]{"跑步热身", "全身", "这是跑步热身课程"};
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class, R.drawable.run, temp_content);
                break;
            case R.id.course_cardView_4:
                temp_content = new String[]{"极速燃脂", "全身", "这是极速燃脂课程"};
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class, R.drawable.fat_reduction, temp_content);
                break;
            case R.id.course_cardView_5:
                temp_content = new String[]{"增肌专项", "全身", "这是增肌专项课程"};
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class, R.drawable.muscle_augmentation, temp_content);
                break;
            case R.id.course_cardView_6:
                temp_content = new String[]{"臀腿练习", "臀部 大腿", "这是臀腿练习课程"};
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class, R.drawable.buttocks_legs, temp_content);
                break;
            case R.id.course_cardView_7:
                temp_content = new String[]{"上肢强化", "手臂 肩膀", "这是上肢强化课程"};
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class, R.drawable.upper_limb, temp_content);
                break;
            case R.id.course_cardView_8:
                temp_content = new String[]{"舒缓放松", "全身", "这是舒缓放松课程"};
                ActivityFunctionUtil.toStartActivity(getActivity(), CourseDetailActivity.class, R.drawable.relax, temp_content);
                break;
            default:
                break;
        }
    }
}
