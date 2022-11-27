package com.example.appointmenttothedoctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AuthPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_page);
    }

    public void onClickBtEnter(View view){
        System.out.println("Enter");
        Intent intent = new Intent(AuthPageActivity.this, MenuPageActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickBtBack(View view){
        System.out.println("Back");
        Intent intent = new Intent(AuthPageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
