package com.example.gymclubapp.util;

import com.example.gymclubapp.config.ErrorCodeConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

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

    /**
     * 用于前端密码等字段验证
     * @param failure_code
     * @return
     */
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

    /**
     * 获取用户请求的Body
     * @param user
     * @return
     */
    public static RequestBody getRequestBody(User user) {
        RequestBody requestBody;
        if (user.getPasswordConfirmation().isEmpty()) {
            requestBody = new FormBody.Builder()
                    .add(user.request_username, user.getUsername())
                    .add(user.request_password, user.getPassword())
                    .build();
        } else {
            requestBody = new FormBody.Builder()
                    .add(user.request_username, user.getUsername())
                    .add(user.request_password, user.getPassword())
                    .add(user.request_password_cfm, user.getPasswordConfirmation())
                    .build();
        }
        return requestBody;
    }

    /**
     * 分析服务器传来的JSON错误信息
     * @param error_msg
     * @return
     */
    public static String parseErrorMessageWithJSON(String error_msg) {
        String msg = "未知错误！";
        try {
            msg = new JSONObject(error_msg).getString("error_msg");
        } catch (JSONException e) {}
        return msg;
    }
}
