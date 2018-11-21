package com.example.gymclubapp.config;

import com.example.gymclubapp.util.FileUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ServerConfig {
    public static String ip = "192.168.2.170";
    public static final String port = "8000";
    public static String current_destination = "";
    public static boolean isSetToOffLine = false;
    private static List<String> serverIPList = new ArrayList<>();

    public static void initServerConfig(InputStream inputStream) {
        serverIPList = FileUtil.getServerIPList(inputStream);
    }

    public static String getAddress(String destination) {
        current_destination = destination;
        return "http://" + ip + ":" + port + destination;
    }

    public static String getAddress() {
        return "http://" + ip + ":" + port;
    }

    public static int getIpCount() {
        return serverIPList.size();
    }

    public static String getIpFromList(int position) {
        if (position < serverIPList.size()) {
            return serverIPList.size() == 0 ? "" : serverIPList.get(position);
        } else {
            return "";
        }

    }

    public static void updateIP(int position) {
        if (position < serverIPList.size()) {
            ip = serverIPList.get(position);
        }
    }
}
