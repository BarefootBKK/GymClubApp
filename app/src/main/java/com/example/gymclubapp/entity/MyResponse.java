package com.example.gymclubapp.entity;

public class MyResponse {
    private int code;
    private String data;

    public MyResponse(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String str = "code=" + this.code + "\ndata=" + this.data;
        return str;
    }
}
