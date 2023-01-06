package com.example.appointmenttothedoctor.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.appointmenttothedoctor.R;

public class SplashPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        Intent intent = new Intent(SplashPageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}