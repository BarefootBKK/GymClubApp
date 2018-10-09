package com.example.gymclubapp.activity;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gymclubapp.R;

public class SignUpActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setActivityToolbar(R.id.signUpToolbar, true, false);

        Button button_sign_up = findViewById(R.id.button_register);
        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
