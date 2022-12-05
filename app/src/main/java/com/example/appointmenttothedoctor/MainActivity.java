package com.example.appointmenttothedoctor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        onStart();
        setContentView(R.layout.activity_main);
    }

     public void onClickBtRegs(View view) {
         Log.d("onClick", "Regs");
                Intent intent = new Intent(MainActivity.this, RegsPageActivity.class);
                startActivity(intent);
         finish();
     }

     public void onClickBtAuth(View view){
         Log.d("onClick", "Auth");
         Intent intent = new Intent(MainActivity.this, AuthPageActivity.class);
         startActivity(intent);
         finish();
    }

    //При инициализации Activity проверяет вошел ли пользователь в данный момент
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Log.d("onCreate", "Regs");
            Intent intent = new Intent(MainActivity.this, MenuPageActivity.class);
            startActivity(intent);
            finish();
            reload();
        }
    }

    private void reload() { }
}