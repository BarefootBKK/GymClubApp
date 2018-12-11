package com.example.gymclubapp.config;

/**
 * 基础配置信息
 */
public class BasicConfig {
    public static final String INTENT_DATA_NAME = "extra_data"; // 用于Intent数据传递是的name
    public static final int CLASS_COURSE = 1;           // Class类型：Course
    public static final int CLASS_COACH = 2;            // Class类型：Coach
    public static final int CLASS_PROFILE = 3;            // Class类型：Coach
    public static boolean isDatabaseLocked = false;     // 数据库是否正在被占用，默认启用FCFS模式
    public static final String TAG = "测试";
}
