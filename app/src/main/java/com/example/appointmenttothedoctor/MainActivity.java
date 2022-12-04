package com.example.appointmenttothedoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

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
            //Log.d("onCreate", "start");
            Log.d("onCreate", "Regs");
            Intent intent = new Intent(MainActivity.this, MenuPageActivity.class);
            startActivity(intent);
            finish();
            reload();
        }
    }



    private void reload() { }
}