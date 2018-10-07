package com.example.gymclubapp.entity;

public class CourseContent {

    private String courseTitle;
    private String trainingPart;
    private String courseInstruction;

    public CourseContent(String courseTitle, String trainingPart, String courseInstruction) {
        this.courseTitle = courseTitle;
        this.trainingPart = trainingPart;
        this.courseInstruction = courseInstruction;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getTrainingPart() {
        return trainingPart;
    }

    public void setTrainingPart(String trainingPart) {
        this.trainingPart = trainingPart;
    }

    public String getCourseInstruction() {
        return courseInstruction;
    }

    public void setCourseInstruction(String courseInstruction) {
        this.courseInstruction = courseInstruction;
    }

    public String[] toArray() {
        return new String[]{courseTitle, trainingPart, courseInstruction};
    }
}
