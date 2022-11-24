package com.example.appointmenttothedoctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appointmenttothedoctor.R;
import com.google.firebase.auth.FirebaseAuth;

public class AuthPageActivity extends AppCompatActivity {

    Button btEnter;
    Button btBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_page);
        btEnter = findViewById(R.id.btEnter);
        btBack = findViewById(R.id.btBack);


        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Переход назад
                Intent intent = new Intent(AuthPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
