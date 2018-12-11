package com.example.gymclubapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.LitePalSupport;

public class Coach extends LitePalSupport implements Parcelable {
    private String coachName;       // 教练名字
    private String coachBirthday;   // 教练生日
    private String coachIntro;      // 教练介绍
    private String coachSignature;  // 教练个性签名
    private String coachTitle;      // 教练头衔
    private String coachHeadImg;    // 教练头像
    private String coachExtraImg;   // 教练其他图片
    private String coachHaveCourse; // 教练所开的课程
    private String coachCourseType; // 教练的主要课程类型
    private String studentNum;      // 教练的学生人数
    private int coachHeadingImg;    // 支持显示的本地资源图片文件id

    public Coach(){
    }

    public Coach(String coachName, String coachSignature, String coachHeadImg, String studentNum) {
        this.coachName = coachName;
        this.coachSignature = coachSignature;
        this.coachHeadImg = coachHeadImg;
        this.studentNum = studentNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(coachName);
        dest.writeString(coachBirthday);
        dest.writeString(coachIntro);
        dest.writeString(coachSignature);
        dest.writeString(coachTitle);
        dest.writeString(coachHeadImg);
        dest.writeString(coachExtraImg);
        dest.writeString(coachHaveCourse);
        dest.writeString(coachCourseType);
        dest.writeString(studentNum);
        dest.writeInt(coachHeadingImg);
    }

    public static final Parcelable.Creator<Coach> CREATOR = new Parcelable.Creator<Coach>() {
        @Override
        public Coach createFromParcel(Parcel source) {
            Coach coach = new Coach();
            coach.coachName = source.readString();
            coach.coachBirthday = source.readString();
            coach.coachIntro = source.readString();
            coach.coachSignature = source.readString();
            coach.coachTitle = source.readString();
            coach.coachHeadImg = source.readString();
            coach.coachExtraImg = source.readString();
            coach.coachHaveCourse = source.readString();
            coach.coachCourseType = source.readString();
            coach.studentNum = source.readString();
            coach.coachHeadingImg = source.readInt();
            return coach;
        }

        @Override
        public Coach[] newArray(int size) {
            return new Coach[size];
        }
    };

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachBirthday() {
        return coachBirthday;
    }

    public void setCoachBirthday(String coachBirthday) {
        this.coachBirthday = coachBirthday;
    }

    public String getCoachIntro() {
        return coachIntro;
    }

    public void setCoachIntro(String coachIntro) {
        this.coachIntro = coachIntro;
    }

    public String getCoachSignature() {
        return coachSignature;
    }

    public void setCoachSignature(String coachSignature) {
        this.coachSignature = coachSignature;
    }

    public String getCoachTitle() {
        return coachTitle;
    }

    public void setCoachTitle(String coachTitle) {
        this.coachTitle = coachTitle;
    }

    public String getCoachHeadImg() {
        return coachHeadImg;
    }

    public void setCoachHeadImg(String coachHeadImg) {
        this.coachHeadImg = coachHeadImg;
    }

    public String getCoachExtraImg() {
        return coachExtraImg;
    }

    public void setCoachExtraImg(String coachExtraImg) {
        this.coachExtraImg = coachExtraImg;
    }

    public String getCoachHaveCourse() {
        return coachHaveCourse;
    }

    public void setCoachHaveCourse(String coachHaveCourse) {
        this.coachHaveCourse = coachHaveCourse;
    }

    public String getCoachCourseType() {
        return coachCourseType;
    }

    public void setCoachCourseType(String coachCourseType) {
        this.coachCourseType = coachCourseType;
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
}
