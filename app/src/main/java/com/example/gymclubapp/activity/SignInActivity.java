package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gymclubapp.R;
import com.example.gymclubapp.config.HttpConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.controller.ActivityController;
import com.example.gymclubapp.entity.NetworkTask;
import com.example.gymclubapp.entity.User;
import com.example.gymclubapp.entity.NetworkTask;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.util.AccountUtil;
import com.example.gymclubapp.util.ActivityFunctionUtil;
import com.example.gymclubapp.util.ToastUtil;

public class SignInActivity extends BaseActivity implements View.OnClickListener, HttpListener{
    private final static String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button btn_sign_in = (Button) findViewById(R.id.signInButtonIn);
        Button btn_sign_up = (Button) findViewById(R.id.signInButtonUp);

        btn_sign_in.setOnClickListener(this);
        btn_sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInButtonIn:
                User user = getUser();
                int verify_code = AccountUtil.isValid(user);
                if (verify_code == 0 && ServerConfig.isValid()) {
                    NetworkTask userTask = new NetworkTask(ServerConfig.getAddress("/login"),
                            HttpConfig.POST, this);
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
            default:
                break;
        }
    }

    @Override
    public void onMessage(String jsonData) {

    }

    @Override
    public void onSuccess() {
        ActivityFunctionUtil.setIsLogin(true);
        ActivityFunctionUtil.toStartActivity(this, MainActivity.class, -1, "");
        this.finish();
    }

    @Override
    public void onFailure(int failure_code) {
        ToastUtil.showToast(SignInActivity.this,
                AccountUtil.getAccountFailureMessage(failure_code));
    }

    private User getUser() {
        String username = ((EditText)findViewById(R.id.signInEditUser)).getText().toString();
        String password = ((EditText)findViewById(R.id.signInEditPassword)).getText().toString();
        return new User(username, password);
    }
}
