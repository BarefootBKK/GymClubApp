package com.example.gymclubapp.entity;

public class Coach {
    private String coachName;
    private String coachDescription;
    private String studentNum;
    private int coachHeadingImg;
    private String coachImageUrl;

    public Coach(){
    }

    public Coach(String coachName, String coachDescription, String studentNum, String coachImageUrl) {
        this.coachName = coachName;
        this.coachDescription = coachDescription;
        this.studentNum = studentNum;
        this.coachImageUrl = coachImageUrl;
    }

    public Coach(String coachName, String coachDescription, String studentNum, int coachHeadingImg) {
        this.coachName = coachName;
        this.coachDescription = coachDescription;
        this.studentNum = studentNum;
        this.coachHeadingImg = coachHeadingImg;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachDescription() {
        return coachDescription;
    }

    public void setCoachDescription(String coachDescription) {
        this.coachDescription = coachDescription;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public int getCoachHeadingImg() {
        return coachHeadingImg;
    }

    public void setCoachHeadingImg(int coachHeadingImg) {
        this.coachHeadingImg = coachHeadingImg;
    }

    public String getCoachImageUrl() {
        return coachImageUrl;
    }

    public void setCoachImageUrl(String coachImageUrl) {
        this.coachImageUrl = coachImageUrl;
    }
}
