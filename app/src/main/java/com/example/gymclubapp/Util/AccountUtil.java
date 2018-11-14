package com.example.gymclubapp.util;

import com.example.gymclubapp.config.ErrorCodeConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.User;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AccountUtil {

    /**
     * 账号验证
     * 账号或密码为空，返回 -1
     * 两次密码不匹配，返回-2
     * 验证通过，返回0
     * @param user
     * @return
     */
    public static int isValid(User user) {
        if (user.getUsername().isEmpty() ||
            user.getPassword().isEmpty()) {
            return ErrorCodeConfig.ACC_PASSWORD_NULL;
        } else if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            return ErrorCodeConfig.PASSWORD_MISMATCH;
        }
        return 0;
    }

    public static String getAccountFailureMessage(int failure_code) {
        if (failure_code == ErrorCodeConfig.ACC_PASSWORD_NULL) {
            return "账号或密码不能为空！";
        } else if (failure_code == ErrorCodeConfig.PASSWORD_MISMATCH) {
            return "两次密码不匹配！";
        } else if (failure_code == ErrorCodeConfig.ACC_NOT_EXIST) {
            return "账号不存在！";
        } else if (failure_code == ErrorCodeConfig.ACC_EXISTED) {
            return "该账号已被注册！";
        } else if (failure_code == ErrorCodeConfig.ACC_INVALID) {
            return "账号格式错误！";
        } else if (failure_code == ErrorCodeConfig.PASSWORD_WRONG) {
            return "密码错误！";
        } else if (failure_code == ErrorCodeConfig.SERVER_UNAVAILABLE) {
            return "无法连接服务器！" + ServerConfig.getAddress(ServerConfig.current_destination);
        } else if (failure_code == ErrorCodeConfig.NET_DATA_LOSS) {
            return "数据传输错误！";
        }
        return "未知错误！";
    }

    public static RequestBody getRequestBody(User user) {
        RequestBody requestBody = new FormBody.Builder()
                .add(user.request_username, user.getUsername())
                .add(user.request_password, user.getPassword())
                .build();
        return requestBody;
    }
}
