package com.example.gymclubapp.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseName;          // 课程名
    private int imageId;                // 图片id
    private List<String> videoUrlList;  // 课程视频列表

    public Course(String courseName, int imageId) {
        this.courseName = courseName;
        this.imageId = imageId;
        this.videoUrlList = new ArrayList<>();
    }

    public Course(String courseName, int imageId, List<String> videoUrlList) {
        this.courseName = courseName;
        this.imageId = imageId;
        this.videoUrlList = videoUrlList;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setVideoUrlList(List<String> videoUrlList) {
        this.videoUrlList = videoUrlList;
    }

    public List<String> getVideoUrlList() {
        return videoUrlList;
    }

    public int getVideoNum() {
        return this.videoUrlList.size();
    }
}
