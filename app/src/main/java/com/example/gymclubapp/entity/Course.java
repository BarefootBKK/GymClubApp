package com.example.gymclubapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.LitePalSupport;

public class Course extends LitePalSupport implements Parcelable{
    private String courseName;
    private String courseIntro;
    private String courseTrainingPart;
    private String courseHeadImg;
    private String coursePoster;

    public Course() {
    }

    public Course(String courseName, String courseIntro, String courseTrainingPart, String courseHeadImg, String coursePoster) {
        this.courseName = courseName;
        this.courseIntro = courseIntro;
        this.courseTrainingPart = courseTrainingPart;
        this.courseHeadImg = courseHeadImg;
        this.coursePoster = coursePoster;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public String getCourseTrainingPart() {
        return courseTrainingPart;
    }

    public void setCourseTrainingPart(String courseTrainingPart) {
        this.courseTrainingPart = courseTrainingPart;
    }

    public String getCourseHeadImg() {
        return courseHeadImg;
    }

    public void setCourseHeadImg(String courseHeadImg) {
        this.courseHeadImg = courseHeadImg;
    }

    public String getCoursePoster() {
        return coursePoster;
    }

    public void setCoursePoster(String coursePoster) {
        this.coursePoster = coursePoster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseName);
        dest.writeString(courseIntro);
        dest.writeString(courseTrainingPart);
        dest.writeString(courseHeadImg);
        dest.writeString(coursePoster);
    }

    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel source) {
            Course course = new Course();
            course.courseName = source.readString();
            course.courseIntro = source.readString();
            course.courseTrainingPart = source.readString();
            course.courseHeadImg = source.readString();
            course.coursePoster = source.readString();
            return course;
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };
}
