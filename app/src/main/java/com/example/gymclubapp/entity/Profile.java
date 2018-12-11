package com.example.gymclubapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.LitePalSupport;

public class Profile extends LitePalSupport implements Parcelable {
    private String username;
    private String nickname;
    private String fansNum;
    private String subscribeNum;
    private String likedNum;
    private String headImageUrl;

    public Profile() {

    }

    public Profile(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(nickname);
        dest.writeString(fansNum);
        dest.writeString(subscribeNum);
        dest.writeString(likedNum);
        dest.writeString(headImageUrl);
    }

    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            Profile profile = new Profile();
            profile.username = source.readString();
            profile.nickname = source.readString();
            profile.fansNum = source.readString();
            profile.subscribeNum = source.readString();
            profile.likedNum = source.readString();
            profile.headImageUrl = source.readString();
            return profile;
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public String getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(String subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    public String getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(String likedNum) {
        this.likedNum = likedNum;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }
}
