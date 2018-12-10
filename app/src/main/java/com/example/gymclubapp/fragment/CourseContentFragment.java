package com.example.gymclubapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.TextView;

import com.example.gymclubapp.R;
import com.example.gymclubapp.config.NetConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.Coach;
import com.example.gymclubapp.entity.Course;
import com.example.gymclubapp.entity.DatabaseRunnable;
import com.example.gymclubapp.entity.NetworkTask;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CourseContentFragment extends BaseFragment implements
        HttpListener, SwipeRefreshLayout.OnRefreshListener{
    private static Context mContext;
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected TextView error_msg;
    protected boolean isLoaded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        mContext = getContext();
        super.toolbarId = R.id.toolbarCourse;
        super.menuId = R.menu.toolbar_course;
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 获取资源成功
     * @param jsonData
     */
    @Override
    public void onSuccess(String jsonData) {
        // 子类需要重载这个方法，否则无法执行刷新任务
    }

    /**
     * 获取服务器资源失败
     * @param failure_code
     * @param failure_data
     */
    @Override
    public void onFailure(int failure_code, String failure_data) {
        if (failure_code == 101) {
            ToastUtil.showToast(getActivity(), "服务器无数据响应!");
        } else {
            ToastUtil.showToast(getActivity(), "数据更新失败！");
        }
        if (!isLoaded) {
            hideRecyclerView();
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        loadData();
    }

    /**
     * 加载数据
     */
    protected void loadData() {
        // 子类需要重载这个方法，否则无法执行加载数据任务
    }

    protected void initTrainingListData(int recyclerViewId, int swipeContainerId, int errorViewId) {
        error_msg = getActivity().findViewById(errorViewId);
        // recyclerView
        recyclerView = getActivity().findViewById(recyclerViewId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 刷新
        swipeRefreshLayout = getActivity().findViewById(swipeContainerId);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeRefreshLayout.setOnRefreshListener(this);
        loadData();
    }

    /**
     * 执行网络任务
     * @param url
     */
    protected void startNetworkTaskByGET(String url) {
        NetworkTask networkTask = new NetworkTask(url, NetConfig.GET, this);
        networkTask.execute();
    }

    /**
     * 更新列表信息
     * @param adapter
     */
    protected void updateTrainingListData(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        showRecyclerView();
        swipeRefreshLayout.setRefreshing(false);
        isLoaded = true;
        ToastUtil.showToast(getActivity(), "数据已更新");
    }

    /**
     * 显示RecyclerView
     */
    protected void showRecyclerView() {
        recyclerView.smoothScrollToPosition(0);
        recyclerView.bringToFront();
        recyclerView.setVisibility(View.VISIBLE);
        error_msg.setVisibility(View.GONE);
    }

    /**
     * 隐藏RecyclerView
     */
    protected void hideRecyclerView() {
        recyclerView.setVisibility(View.GONE);
        error_msg.bringToFront();
        error_msg.setVisibility(View.VISIBLE);
    }

    /**
     * 保存缓存信息
     * @param databaseRunnable
     */
    protected void saveCacheData(DatabaseRunnable databaseRunnable) {
        new Thread(databaseRunnable).start();
    }

    /**
     * 加载缓存信息
     * @param adapter
     */
    protected void loadCache(RecyclerView.Adapter adapter) {
        if (!isLoaded) {
            if (adapter.getItemCount() > 0) {
                updateTrainingListData(adapter);
            } else {
                hideRecyclerView();
            }
        } else {
            swipeRefreshLayout.setRefreshing(false);
            ToastUtil.showToast(getActivity(), "请检查网络连接!");
        }
    }

    /**
     * 解析Coach的json数据
     * @param jsonData
     * @return
     */
    protected List<Coach> parseCoachWithJSON(String jsonData) {
        List<Coach> coachList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                Coach coach = new Coach();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                coach.setCoachName(jsonObject.getString("coachName"));
                coach.setCoachBirthday(jsonObject.getString("coachBirthday"));
                coach.setCoachIntro(jsonObject.getString("coachIntro"));
                coach.setCoachSignature(jsonObject.getString("coachSignature"));
                coach.setCoachTitle(jsonObject.getString("coachTitle"));
                coach.setCoachHaveCourse(jsonObject.getString("coachHaveCourse"));
                coach.setCoachCourseType(jsonObject.getString("coachCourseType"));
                coach.setStudentNum(jsonObject.getString("studentNum"));
                coach.setCoachHeadImg(ServerConfig.getAddress("/static") +
                        jsonObject.getString("coachHeadImg"));
                coach.setCoachExtraImg(ServerConfig.getAddress("/static") +
                        jsonObject.getString("coachExtraImg"));
                coachList.add(coach);
            }
        } catch (Exception e) {
            ToastUtil.showToast(CourseContentFragment.mContext, "无法解析的json数据!" + jsonData);
        }
        return coachList;
    }

    /**
     * 解析Course的json数据
     * @param jsonData
     * @return
     */
    protected List<Course> parseCourseWithJSON(String jsonData) {
        List<Course> courseList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                Course course = new Course();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                course.setCourseName(jsonObject.getString("courseName"));
                course.setCourseIntro(jsonObject.getString("courseIntro"));
                course.setCourseTrainingPart(jsonObject.getString("courseTrainingPart"));
                course.setCourseHeadImg(ServerConfig.getAddress("/static") +
                        jsonObject.getString("courseHeadImg"));
                course.setCoursePoster(ServerConfig.getAddress("/static") +
                        jsonObject.getString("coursePoster"));
                courseList.add(course);
            }
        } catch (Exception e) {
            ToastUtil.showToast(CourseContentFragment.mContext, "无法解析的json数据!" + jsonData);
        }
        return courseList;
    }
}
