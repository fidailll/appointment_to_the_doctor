package com.example.appointmenttothedoctor;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegsPageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regs_page);
}

    public void onClickBtSend(View view) {
        System.out.println("Send");

    }

    public void onClickBtBack(View view) {
        System.out.println("Back");
        Intent intent = new Intent(RegsPageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

