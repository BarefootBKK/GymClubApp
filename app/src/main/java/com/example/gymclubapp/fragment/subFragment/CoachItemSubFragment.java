package com.example.gymclubapp.fragment.subFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymclubapp.R;
import com.example.gymclubapp.adapters.CoachAdapter;
import com.example.gymclubapp.adapters.CourseAdapter;
import com.example.gymclubapp.config.HttpConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.Coach;
import com.example.gymclubapp.entity.NetworkTask;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.util.HttpUtil;
import com.example.gymclubapp.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public class CoachItemSubFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.coach_recyclerview, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        NetworkTask networkTask = new NetworkTask(ServerConfig.getAddress("/coach"), HttpConfig.GET,
                new HttpListener() {
                    @Override
                    public void onMessage(String jsonData) {
                        CoachAdapter coachAdapter = new CoachAdapter(parseJSOWithJSONObject(jsonData), R.layout.coach_item, getActivity());
                        RecyclerView recyclerView = getActivity().findViewById(R.id.coach_ry);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(coachAdapter);
                    }

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(int failure_code) {
                        Log.d("测试", "onFailure: ");
                    }
                });
        networkTask.execute();

        super.onActivityCreated(savedInstanceState);
    }

    private List<Coach> parseJSOWithJSONObject(String jasonData) {
        Log.d("测试", "parseJSOWithJSONObject: " + jasonData);
        List<Coach> coachList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            coachList = gson.fromJson(jasonData,
                    new TypeToken<List<Coach>>(){}.getType());
        } catch (Exception e) {
            ToastUtil.showToast(getActivity(), "无法解析的json数据!" + jasonData);
        }
        return coachList;
    }
}
