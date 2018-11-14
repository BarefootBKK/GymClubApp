package com.example.gymclubapp.activity;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymclubapp.R;
import com.example.gymclubapp.config.HttpConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.NetworkTask;
import com.example.gymclubapp.entity.User;
import com.example.gymclubapp.entity.NetworkTask;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.util.AccountUtil;
import com.example.gymclubapp.util.ActivityFunctionUtil;
import com.example.gymclubapp.util.ToastUtil;

public class SignUpActivity extends BaseActivity implements HttpListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setActivityToolbar(R.id.signUpToolbar, true, false);

        Button button_sign_up = findViewById(R.id.button_register);
        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ServerConfig.isSetToOffLine) {
                    User user = getUser();
                    int verify_code = AccountUtil.isValid(user);
                    if (verify_code == 0 && ServerConfig.isValid()) {
                        NetworkTask userTask = new NetworkTask(ServerConfig.getAddress("/register"),
                                HttpConfig.POST, SignUpActivity.this);
                        userTask.execute(AccountUtil.getRequestBody(user));
                    } else {
                        if (verify_code != 0) {
                            ToastUtil.showToast(SignUpActivity.this,
                                    AccountUtil.getAccountFailureMessage(verify_code));
                        } else {
                            ToastUtil.showToast(SignUpActivity.this, "服务器参数出错");
                        }
                    }
                } else {
                    ToastUtil.showToast(SignUpActivity.this, "当前处于离线模式，请到程序里更改！");
                }
            }
        });
    }

    @Override
    public void onMessage(String jsonData) {

    }

    @Override
    public void onSuccess() {
        ActivityFunctionUtil.setIsLogin(true);
        ActivityFunctionUtil.toStartActivity(this, SignInActivity.class, -1, "");
        this.finish();
    }

    @Override
    public void onFailure(int failure_code) {
        ToastUtil.showToast(this, AccountUtil.getAccountFailureMessage(failure_code));
    }

    /**
     * 获取User
     * @return user
     */
    private User getUser() {
        String username = ((EditText) findViewById(R.id.signUpEditUser))
                .getText().toString();
        String password = ((EditText) findViewById(R.id.signUpEditPassword))
                .getText().toString();
        String password_cfm = ((EditText) findViewById(R.id.signUpEditPasswordCfm))
                .getText().toString();
        return new User(username, password, password_cfm);
    }
}
