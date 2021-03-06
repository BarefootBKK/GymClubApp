package com.example.gymclubapp.adapters;

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
import com.example.gymclubapp.activity.CoachDetailActivity;
import com.example.gymclubapp.entity.Coach;
import com.example.gymclubapp.util.ActivityFunctionUtil;
import com.squareup.picasso.Picasso;

public class CoachAdapter extends RecyclerView.Adapter<CoachAdapter.ViewHolder> {

    private List<Coach> coachList;
    private int rowLayout;
    private Context mContext;

    public CoachAdapter(List<Coach> coachList, int rowLayout, Context mContext) {
        this.coachList = coachList;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Coach coach = coachList.get(position);
        holder.coachName.setText(coach.getCoachName());
        holder.coachDescription.setText(coach.getCoachSignature());
        holder.studentNum.setText(coach.getStudentNum() + "人");
        Picasso.get().load(coach.getCoachHeadImg()).into(holder.coachImage);
        holder.coachCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityFunctionUtil.toStartActivityByParcelable(mContext, CoachDetailActivity.class, coach);
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
        return coachList == null ? 0 : coachList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView coachImage;
        public TextView coachName;
        public TextView coachDescription;
        public TextView studentNum;
        public CardView coachCard;
        public ViewHolder(View itemView) {
            super(itemView);
            coachImage = itemView.findViewById(R.id.coach_img);
            coachName = itemView.findViewById(R.id.coach_name);
            coachDescription = itemView.findViewById(R.id.coach_description);
            studentNum = itemView.findViewById(R.id.coach_student_num);
            coachCard = itemView.findViewById(R.id.coach_card);
        }
    }
}

