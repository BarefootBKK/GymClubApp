package com.example.gymclubapp.config;

public class ServerConfig {
    public static final String ip = "192.168.2.170";
    public static final String port = "8000";
    public static String current_destination = "";
    public static boolean isSetToOffLine = false;

    public static String getAddress(String destination) {
        current_destination = destination;
        return "http://" + ip + ":" + port + destination;
    }

    public static String getAddress() {
        return "http://" + ip + ":" + port;
    }

}
