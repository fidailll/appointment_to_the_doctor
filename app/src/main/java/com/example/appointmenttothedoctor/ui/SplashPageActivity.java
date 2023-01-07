package com.example.appointmenttothedoctor.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.appointmenttothedoctor.R;
import com.yandex.mapkit.MapKitFactory;

public class SplashPageActivity extends AppCompatActivity {
    private final String MAPKIT_API_KEY = "2263dbfd-65f2-43a0-a99d-8687a56af4bd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        Intent intent = new Intent(SplashPageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}