package com.example.gymclubapp.interfaces;

public interface HttpListener {
    void onMessage(String jsonData);
    void onSuccess();
    void onFailure(int failure_code, String failure_data);
}
