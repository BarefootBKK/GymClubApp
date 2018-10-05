package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.gymclubapp.R;
import com.example.gymclubapp.Util.ActivityFunctionUtil;

public class SignInActivity extends BaseActivity implements View.OnClickListener{
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
                ActivityFunctionUtil.setIsLogin(true);
                ActivityFunctionUtil.toStartActivity(this, MainActivity.class, null, -1);
                this.finish();
                break;
            case R.id.signInButtonUp:
                ActivityFunctionUtil.toStartActivity(this, SignUpActivity.class, null, -1);
                break;
            default:
                break;
        }
    }
}
