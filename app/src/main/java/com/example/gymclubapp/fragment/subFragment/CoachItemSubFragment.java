package com.example.gymclubapp.fragment.subFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymclubapp.R;
import com.example.gymclubapp.adapters.CoachAdapter;
import com.example.gymclubapp.config.BasicConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.Coach;
import com.example.gymclubapp.entity.DatabaseRunnable;
import com.example.gymclubapp.fragment.CourseContentFragment;
import com.example.gymclubapp.util.HttpUtil;

import org.litepal.LitePal;

import java.util.List;

public class CoachItemSubFragment extends CourseContentFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.coach_recyclerview, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initTrainingListData(R.id.coach_ry, R.id.coach_swipeContainer, R.id.coach_swipe_error_msg);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSuccess(String jsonData) {
        // 获取解析数据
        List<Coach> coachList = parseCoachWithJSON(jsonData);
        // 更新列表
        super.updateTrainingListData(new CoachAdapter(coachList, R.layout.coach_item, getActivity()));
        // 保存缓存信息
        super.saveCacheData(new DatabaseRunnable<>(coachList, BasicConfig.CLASS_COACH));
    }

    @Override
    protected void loadData() {
        if (HttpUtil.isNetConnect(getActivity())) {
            startNetworkTaskByGET(ServerConfig.getAddress("/coach"));
        } else {
            // 读取缓存
            List<Coach> coachList = LitePal.findAll(Coach.class);
            RecyclerView.Adapter adapter = new CoachAdapter(coachList, R.layout.coach_item, getActivity());
            loadCache(adapter);
        }
    }
}
