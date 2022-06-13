package com.example.vaxxmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private Button mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initComponents();

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });
    }

    private void initComponents() {
        mLogout = (Button) findViewById(R.id.buttonLogout);

        mAuth = FirebaseAuth.getInstance();
    }
}