package com.example.appointmenttothedoctor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthPageActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    EditText emailEditText;
    EditText passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_page);
        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.editTextEmailAddress);
        passEditText = findViewById(R.id.editTextPassword);
    }

    public void onClickBtEnter(View view){
        Log.d("onClick", "Enter");

        Log.d("emailEditText", emailEditText.getText().toString());
        Log.d("passEditText", passEditText.getText().toString());

        checkEditText(emailEditText.getText().toString(), passEditText.getText().toString());
    }

    public void onClickBtBack(View view){
        Log.d("onClick", "Back");
        Intent intent = new Intent(AuthPageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickBtForgotYourPassword(View view){
        Log.d("onClick", "BtForgotYourPassword");
        Intent intent = new Intent(AuthPageActivity.this, PassRecoveryActivity.class);
        startActivity(intent);
        finish();
    }

////    //При инициализации Activity проверяет вошел ли пользователь в данный момент
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            reload();
//        }
//    }

    /// Проверка на наличия текста в EditText
    private void checkEditText(String email, String pass){
        if (email.length() == 0) {
            Toast.makeText(AuthPageActivity.this, "Заполните поле Email!",
                    Toast.LENGTH_SHORT).show();
            emailEditText.setError("Заполните поле Email!");
        } else if (pass.length() == 0) {
            Toast.makeText(AuthPageActivity.this, "Заполните поле Пароль!",
                    Toast.LENGTH_SHORT).show();
            passEditText.setError("Заполните поле Пароль!");

        } else {
            signInAccount(email, pass);
        }
    }

    /// Войти в аккаунт
    private void signInAccount(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent intent = new Intent(AuthPageActivity.this, MenuPageActivity.class);
                             startActivity(intent);
                              finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthPageActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }

}
