package com.example.gymclubapp.config;

/**
 * 全局网络配置信息
 */
public class NetConfig {
    public static final int GET = 1;                 // GET方法
    public static final int POST = 2;                // POST方法
    public static final int NETWORK_NONE = -1;      // 网络类型：无网络
    public static final int NETWORK_MOBILE = 0;     // 网络类型：移动网络
    public static final int NETWORK_WIFI = 1;       // 网络类型：WIFI网络
    public static int CURRENT_NETWORK_TYPE = -1;     // 当前网络类型， 默认为“无网络”
    public static boolean isNetConnect = false;     // 是否已连接互联网，默认为false
}
