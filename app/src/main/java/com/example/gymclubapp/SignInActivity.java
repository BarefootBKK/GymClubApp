package com.example.gymclubapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button button_sign_in = findViewById(R.id.button_sign_in);
        Button button_sign_up = findViewById(R.id.button_sign_up);

        setButtonListener(button_sign_in, this, MainActivity.class);
        setButtonListener(button_sign_up, this, MainActivity.class);
    }

    private void setButtonListener(Button button, final Context context, final Class targetClass) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, targetClass);
                ActivityController.activity_signIn = SignInActivity.this;
                startActivity(intent);
            }
        });
    }
}
