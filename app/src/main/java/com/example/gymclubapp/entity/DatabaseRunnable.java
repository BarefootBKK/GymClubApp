package com.example.gymclubapp.entity;

import com.example.gymclubapp.config.BasicConfig;

import org.litepal.LitePal;

import java.util.List;

public class DatabaseRunnable<T> implements Runnable {

    private List<T> dataList;
    private int classType;

    public DatabaseRunnable(List<T> dataList, int classType) {
        this.dataList = dataList;
        this.classType = classType;
    }

    @Override
    public void run() {
        while (true) {
            if (!BasicConfig.isDatabaseLocked) {
                BasicConfig.isDatabaseLocked = true;
                if (classType == BasicConfig.CLASS_COACH) {
                    saveCoachData();
                } else if (classType == BasicConfig.CLASS_COURSE) {
                    saveCourseData();
                }
                BasicConfig.isDatabaseLocked = false;
                break;
            }
        }
    }

    private void saveCoachData() {
        List<Coach> coachList = (List<Coach>) this.dataList;
        LitePal.deleteAll("Coach");
        for (Coach coach : coachList) {
            coach.save();
        }
    }

    private void saveCourseData() {
        List<Course> courseList = (List<Course>) this.dataList;
        LitePal.deleteAll("Course");
        for (Course course : courseList) {
            course.save();
        }
    }
}
