package com.example.gymclubapp.config;

import com.example.gymclubapp.util.FileUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务器配置信息
 * 支持从文件读取多服务器IP，在/res/raw/ip.txt中更改
 */
public class ServerConfig {
    // 服务器默认地址和端口
    public static String ip = "vgfame.top";
    public static final String port = "8000";
    // url的Destination
    public static String current_destination = "";
    // 设置离线模式，默认为false
    public static boolean isSetToOffLine = false;
    // 是否连接到服务器
    public static boolean isConnected = true;
    // 从文件中读取到的IP列表
    private static List<String> serverIPList = new ArrayList<>();

    /**
     * 从文件中读取IP列表
     * @param inputStream
     */
    public static void initServerConfig(InputStream inputStream) {
        serverIPList = FileUtil.getServerIPList(inputStream);
    }

    /**
     * 获取URL
     * @param destination
     * @return
     */
    public static String getAddress(String destination) {
        current_destination = destination;
        return "http://" + ip + ":" + port + destination;
    }

    /**
     * 获取URL地址
     * @return
     */
    public static String getAddress() {
        return "http://" + ip + ":" + port;
    }

    /**
     * 获取IP列表中IP个数
     * @return
     */
    public static int getIpCount() {
        return serverIPList.size();
    }

    /**
     * 获取IP列表中指定位置的IP
     * @param position
     * @return
     */
    public static String getIpFromList(int position) {
        if (position < serverIPList.size()) {
            return serverIPList.size() == 0 ? "" : serverIPList.get(position);
        } else {
            return "";
        }

    }

    /**
     * 更新当前IP
     * @param position
     */
    public static void updateIP(int position) {
        if (position < serverIPList.size()) {
            ip = serverIPList.get(position);
        }
    }
}
