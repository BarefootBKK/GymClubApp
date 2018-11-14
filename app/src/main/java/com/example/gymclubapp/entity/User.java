package com.example.gymclubapp.entity;

public class User {
    public final String request_username = "username";
    public final String request_password = "password";

    private String username;
    private String password;
    private String password_cfm;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.password_cfm = password;
    }

    public User(String username, String password, String password_cfm) {
        this.username = username;
        this.password = password;
        this.password_cfm = password_cfm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return password_cfm;
    }

    public void setPasswordConfirmation(String password_cfm) {
        this.password_cfm = password_cfm;
    }
}
