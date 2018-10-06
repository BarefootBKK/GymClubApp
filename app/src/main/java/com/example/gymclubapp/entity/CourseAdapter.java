//package com.example.gymclubapp.entity;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//import java.util.List;
//import com.example.gymclubapp.R;
//
//public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
//
//    private List<Course> courseList;
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView courseImage;
//        TextView courseName;
//
//        public ViewHolder(View view) {
//            super(view);
//            courseImage = (ImageView) view.findViewById(R.id.testImageView);
//            courseName = (TextView) view.findViewById(R.id.testTextView);
//        }
//    }
//
//    public CourseAdapter(List<Course> courseList) {
//        this.courseList = courseList;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.testview, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Course course = courseList.get(position);
//        holder.courseImage.setImageResource(course.getImageId());
//        holder.courseName.setText(course.getCourseName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return courseList.size();
//    }
//}
