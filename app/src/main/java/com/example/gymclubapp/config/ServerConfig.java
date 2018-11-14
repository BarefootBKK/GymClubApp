package com.example.gymclubapp.config;

public class ServerConfig {
    public static String ip = "";
    public static String port = "";
    public static String current_destination = "";
    public static boolean isSetToOffLine = true;

    public static String getAddress(String destination) {
        current_destination = destination;
        return "http://" + ip + ":" + port + destination;
    }

    public static String getAddress() {
        return "http://" + ip + ":" + port;
    }

    public static boolean isValid() {
        return (!ip.isEmpty() && !port.isEmpty());
    }
}
