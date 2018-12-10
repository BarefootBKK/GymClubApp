package com.example.gymclubapp.config;

/**
 * 错误码配置信息
 */
public class ErrorCodeConfig {
    // 账号密码相关
    public static final int ACC_PASSWORD_NULL = 1;
    public static final int PASSWORD_MISMATCH = 2;
    public static final int ACC_INVALID = 3;
    public static final int ACC_EXISTED = 4;
    public static final int ACC_NOT_EXIST = 5;
    public static final int PASSWORD_WRONG = 6;
    // 服务器相关
    public static final int IP_NOT_SET = 1;
    public static final int PORT_NOT_SET = 2;
    public static final int SERVER_UNAVAILABLE = -1;
    public static final int NET_DATA_LOSS = -2;
}
