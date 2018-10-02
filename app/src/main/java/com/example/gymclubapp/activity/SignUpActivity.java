package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gymclubapp.R;
import com.example.gymclubapp.controller.ActivityController;

public class SignUpActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActivityController.getObject().setStatusBar(this);

        ImageView img_back = (ImageView) findViewById(R.id.imageViewBack);
        Button button_sign_up = findViewById(R.id.button_register);

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                ActivityController.finishActivity(SignUpActivity.this);
            }
        });
    }
}
