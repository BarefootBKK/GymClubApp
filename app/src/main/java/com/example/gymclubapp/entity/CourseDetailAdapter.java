package com.example.gymclubapp.entity;

public class CourseDetailAdapter {

    private String courseTitle;
    private String bodyName;
    private String courseContent;
    private int posterId;

    public CourseDetailAdapter(String courseTitle, String bodyName, String courseContent, int posterId) {
        this.courseTitle = courseTitle;
        this.bodyName = bodyName;
        this.courseContent = courseContent;
        this.posterId = posterId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getBodyName() {
        return bodyName;
    }

    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }
}
