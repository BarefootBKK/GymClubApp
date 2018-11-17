package com.example.gymclubapp.entity;

public class Profile {
    private String username;
    private String nickname;
    private String fansNum;
    private String subscribeNum;
    private String likedNum;
    private String headImageUrl;

    public Profile(String username) {
        this.username = username;
    }

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
