package com.example.gymclubapp.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 这是一个QQ登录的管理类
 * 用法：
 * 1.首先implements QQLoginManager.QQLoginListener
 *
 * 2.启用构造函数，这里需要传入3个参数：app_id，QQLoginListener和Context
 *      示例：new QQLoginManager(你的app_id, this, this)
 *      这里的两个this，一个是QQLoginListener，一个是Context
 *      ## 当然你可以用另一个构造函数，new QQLoginManager(this, this)
 *      ## 但是接下来需要设置app_id, 调用setAPP_ID(String app_id)方法
 *
 * 3.然后需要在onActivityResult()方法里注册监听器，调用registerResultListener(...)方法
 *
 * 4.然后就可以调用QQ登录函数了：launchQQLogin(Activity activity)，
 *  在重载的三个方法(onQQLoginSuccess(...)等)里执行你的操作吧
 */
public class QQLoginManager {
    private String app_id = "";
    private Tencent mTencent;
    private UserInfo mUserInfo;
    private LocalLoginListener localLoginListener;
    private QQLoginListener qqLoginListener;
    private Context mContext;

    /**
     * 构造函数，不含app_id
     * @param qqLoginListener
     * @param mContext
     */
    public QQLoginManager(QQLoginListener qqLoginListener, Context mContext) {
        this.mContext = mContext;
        this.qqLoginListener = qqLoginListener;
        initData();
    }

    /**
     * 构造函数，包括app_id
     * @param app_id
     * @param qqLoginListener
     * @param mContext
     */
    public QQLoginManager(String app_id, QQLoginListener qqLoginListener, Context mContext) {
        this.app_id = app_id;
        this.qqLoginListener = qqLoginListener;
        this.mContext = mContext;
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        localLoginListener = new LocalLoginListener();
        if (mTencent == null) {
            mTencent = Tencent.createInstance(app_id, mContext);
        }
    }

    /**
     * 设置app_id
     * @param app_id
     */
    public void setAPP_ID(String app_id) {
        this.app_id = app_id;
    }

    /**
     * 注册监听器
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void registerResultListener(int requestCode, int resultCode, @Nullable Intent data) {
        mTencent.onActivityResultData(requestCode, resultCode, data, localLoginListener);
    }

    /**
     * 启动QQ登录
     * @param activity
     */
    public void launchQQLogin(Activity activity) {
        if (!mTencent.isSessionValid()) {
            mTencent.login(activity, "all", localLoginListener);
        } else {
            mTencent.logout(mContext);
            launchQQLogin(activity);
        }
    }

    /**
     * 退出QQ登录
     * @param activity
     */
    public void logoutQQ(Activity activity) {
        if (mTencent.isSessionValid()) {
            mTencent.logout(activity);
        }
    }

    /**
     * QQ登录状态监听器
     */
    public interface QQLoginListener {
        void onQQLoginSuccess(JSONObject jsonObject);
        void onQQLoginCancel();
        void onQQLoginError(UiError uiError);
    }

    /**
     * 本地QQ登录监听器
     */
    private class LocalLoginListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            initOpenIdAndToken(o);
            loadUserInfo();
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }

        /**
         * 初始化openID和access_token
         * @param object
         */
        private void initOpenIdAndToken(Object object) {
            JSONObject jsonObject = (JSONObject) object;
            try {
                String openID = jsonObject.getString("openid");
                String access_token = jsonObject.getString("access_token");
                String expires = jsonObject.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(access_token, expires);
            } catch (JSONException e) {

            }
        }

        /**
         * 加载用户信息
         */
        private void loadUserInfo() {
            QQToken qqToken = mTencent.getQQToken();
            mUserInfo = new UserInfo(mContext, qqToken);
            mUserInfo.getUserInfo(new IUiListener() {
                /**
                 * 登录成功
                 * @param o
                 */
                @Override
                public void onComplete(Object o) {
                    qqLoginListener.onQQLoginSuccess((JSONObject) o);
                }

                /**
                 * 登录出错
                 * @param uiError
                 */
                @Override
                public void onError(UiError uiError) {
                    qqLoginListener.onQQLoginError(uiError);
                }

                /**
                 * 取消登录
                 */
                @Override
                public void onCancel() {
                    qqLoginListener.onQQLoginCancel();
                }
            });
        }
    }
}
