package com.example.gymclubapp.interfaces;

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
