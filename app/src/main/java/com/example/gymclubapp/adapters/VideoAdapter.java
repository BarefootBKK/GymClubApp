package com.example.gymclubapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymclubapp.R;
import com.example.gymclubapp.activity.VideoDetailActivity;
import com.example.gymclubapp.entity.Course;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private List<Course> videoList;
    private int rowLayout;
    private Context mContext;
    public static Activity activity;

    public VideoAdapter(List<Course> videoList, int rowLayout, Context mContext) {
        this.videoList = videoList;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = videoList.get(position);
        Picasso.get().load(course.getCourseHeadImg()).into(holder.videoImg);
        holder.videoTitle.setText(course.getCourseName() + "-" + (position + 1));
        holder.trainFrequency.setText(position + 5 + "次");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoDetailActivity.class);
                mContext.startActivity(intent);
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
        return videoList == null ? 0 : videoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ImageView videoImg;
        public TextView videoTitle;
        public TextView trainFrequency;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.video_cardView);
            videoImg = itemView.findViewById(R.id.video_img);
            videoTitle = itemView.findViewById(R.id.video_title);
            trainFrequency = itemView.findViewById(R.id.train_frequency);
        }
    }
}
