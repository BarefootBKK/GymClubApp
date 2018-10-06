package com.example.gymclubapp.entity;

public class Course {

    private String courseName;
    private int imageId;

    public Course(String courseName, int imageId) {
        this.courseName = courseName;
        this.imageId = imageId;
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
}
