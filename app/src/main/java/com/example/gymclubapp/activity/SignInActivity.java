package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymclubapp.R;
import com.example.gymclubapp.config.BasicConfig;
import com.example.gymclubapp.config.CacheConfig;
import com.example.gymclubapp.config.NetConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.DatabaseRunnable;
import com.example.gymclubapp.entity.NetworkTask;
import com.example.gymclubapp.entity.Profile;
import com.example.gymclubapp.entity.User;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.managers.QQLoginManager;
import com.example.gymclubapp.util.AccountUtil;
import com.example.gymclubapp.util.ActivityFunctionUtil;
import com.example.gymclubapp.util.ToastUtil;
import com.tencent.tauth.Tencent;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends BaseActivity implements
        View.OnClickListener, HttpListener, QQLoginManager.QQLoginListener {

    private TextView textView;
    private QQLoginManager qqLoginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        qqLoginManager = new QQLoginManager("1107957445", this, this);

        Button btn_sign_in = findViewById(R.id.signInButtonIn);
        Button btn_sign_up = findViewById(R.id.signInButtonUp);
        ImageView imageView_qq = findViewById(R.id.signIn_qq);
        ImageView imageView_weibo = findViewById(R.id.signIn_weibo);
        textView = findViewById(R.id.signIn_loadingHint);

        btn_sign_in.setOnClickListener(this);
        btn_sign_up.setOnClickListener(this);
        imageView_qq.setOnClickListener(this);
        imageView_weibo.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        qqLoginManager.registerResultListener(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInButtonIn:
                User user = getUser();
                int verify_code = AccountUtil.isValid(user);
                if (verify_code == 0) {
                    NetworkTask userTask = new NetworkTask(ServerConfig.getAddress("/login"),
                            NetConfig.POST, this);
                    userTask.execute(AccountUtil.getRequestBody(user));
                } else {
                    if (verify_code != 0) {
                        ToastUtil.showToast(SignInActivity.this,
                                AccountUtil.getAccountFailureMessage(verify_code));
                    } else {
                        ToastUtil.showToast(SignInActivity.this, "服务器参数出错");
                    }
                }
                break;
            case R.id.signInButtonUp:
                ActivityFunctionUtil.toStartActivity(this, SignUpActivity.class, -1, "");
                break;
            case R.id.signIn_qq:
                showLoadingHint(true);
                qqLoginManager.launchQQLogin(this);
                break;
            case R.id.signIn_weibo:
                ToastUtil.showToast(this, "未支持该第三方登录，敬请期待...");
                break;
            default:
                break;
        }
    }

    /**
     * 显示加载提示信息
     * @param isShow
     */
    private void showLoadingHint(boolean isShow) {
        if (isShow) {
            textView.setText("正在发起第三方授权，请稍候...");
        } else {
            textView.setText("");
        }
    }

    /**
     * 获取User信息
     * @return User
     */
    private User getUser() {
        String username = ((EditText)findViewById(R.id.signInEditUser)).getText().toString();
        String password = ((EditText)findViewById(R.id.signInEditPassword)).getText().toString();
        return new User(username, password);
    }

    /**
     * 登录成功
     * @param jsonData
     */
    @Override
    public void onSuccess(String jsonData) {
        try {
            Profile profile = new Profile();
            profile.setNickname(new JSONObject(jsonData).getString("nickname"));
            ActivityFunctionUtil.toStartActivityByParcelable(this, MainActivity.class, profile);
            ActivityFunctionUtil.saveDataWithSPByBoolean(this, CacheConfig.LOGIN_CACHE,
                    CacheConfig.LOGIN_CACHE_KEY, true);
            // 保存用户信息的缓存数据
            new Thread(
                    new DatabaseRunnable<>(profile, BasicConfig.CLASS_PROFILE)
            ).start();
            this.finish();
        } catch (JSONException e) {

        }
    }

    /**
     * 登录失败
     * @param failure_code
     * @param failure_data
     */
    @Override
    public void onFailure(int failure_code, String failure_data) {
        ToastUtil.showToast(SignInActivity.this,
                AccountUtil.parseErrorMessageWithJSON(failure_data));
    }

    @Override
    public void onQQLoginSuccess(JSONObject jsonObject) {
        try {
            Profile profile = new Profile();
            profile.setNickname(jsonObject.getString("nickname"));
            profile.setHeadImageUrl(jsonObject.getString("figureurl_qq_2"));
            ActivityFunctionUtil.toStartActivityByParcelable(SignInActivity.this, MainActivity.class, profile);
            ActivityFunctionUtil.saveDataWithSPByBoolean(SignInActivity.this, CacheConfig.LOGIN_CACHE,
                    CacheConfig.LOGIN_CACHE_KEY, true);
            // 保存用户信息的缓存数据
            new Thread(
                    new DatabaseRunnable<>(profile, BasicConfig.CLASS_PROFILE)
            ).start();
            showLoadingHint(false);
            ToastUtil.showToast(this, "登录成功");
            SignInActivity.this.finish();
        } catch (JSONException e) {
            ToastUtil.showToast(SignInActivity.this, e.toString());
        }
    }

    @Override
    public void onQQLoginCancel() {
        ToastUtil.showToast(this, "登录取消");
    }

    @Override
    public void onQQLoginError(UiError uiError) {
        ToastUtil.showToast(this, "登录出错");
    }
}
