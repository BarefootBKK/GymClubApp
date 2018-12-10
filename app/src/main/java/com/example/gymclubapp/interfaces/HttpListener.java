package com.example.gymclubapp.interfaces;

public interface HttpListener {
    void onSuccess(String jsonData);
    void onFailure(int failure_code, String failure_data);
}
