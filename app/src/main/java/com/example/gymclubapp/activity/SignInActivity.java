package com.example.gymclubapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.example.gymclubapp.R;
import com.example.gymclubapp.Util.ActivityFunctionUtil;
import com.example.gymclubapp.controller.ActivityController;

public class SignInActivity extends BaseActivity {
    private final static String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ActivityController.getObject().setStatusBar(this);

        Button btn_sign_in = (Button) findViewById(R.id.button_sign_in);
        Button btn_sign_up = (Button) findViewById(R.id.button_sign_up);

        ActivityController.setButtonListener(btn_sign_in, this, MainActivity.class, true);
        ActivityController.setButtonListener(btn_sign_up, this, SignUpActivity.class, false);
    }
}
