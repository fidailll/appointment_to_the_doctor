package com.example.appointmenttothedoctor;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegsPageActivity extends AppCompatActivity {

    Button btSend;
    Button btBack;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regs_page);

        btSend = findViewById(R.id.btSend);
        btBack = findViewById(R.id.btBack);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Переход назад
                Intent intent = new Intent(RegsPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
    });
}
}

