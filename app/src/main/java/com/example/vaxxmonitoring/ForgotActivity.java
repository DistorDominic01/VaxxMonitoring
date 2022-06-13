package com.example.vaxxmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

public class ForgotActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initComponents();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotActivity.this, LoginActivity.class));
            }
        });
    }

    private void initComponents() {
        mToolbar = (Toolbar) findViewById(R.id.toolBarSignUp);
        mBack = (ImageView) findViewById(R.id.imgBack);
    }
}