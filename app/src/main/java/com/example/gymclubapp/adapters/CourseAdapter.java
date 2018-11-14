package com.example.gymclubapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.example.gymclubapp.R;
import com.example.gymclubapp.activity.CourseDetailActivity;
import com.example.gymclubapp.activity.MainActivity;
import com.example.gymclubapp.entity.Course;
import com.example.gymclubapp.entity.CourseContent;
import com.example.gymclubapp.util.ActivityFunctionUtil;
import com.example.gymclubapp.util.ReadFromFileUtil;
import com.example.gymclubapp.util.ToastUtil;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<Course> courseList;
    private int rowLayout;
    private Context mContext;
    public static Activity activity;

    public CourseAdapter(List<Course> courseList, int rowLayout, Context mContext) {
        this.courseList = courseList;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.courseText.setText(course.getCourseName());
        holder.courseImage.setImageResource(course.getImageId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext, "Clicked!");
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return courseList == null ? 0 : courseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView courseImage;
        public TextView courseText;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            courseImage = itemView.findViewById(R.id.my_course_image);
            courseText = itemView.findViewById(R.id.my_course_title);
            cardView = itemView.findViewById(R.id.coach_card);
        }
    }
}