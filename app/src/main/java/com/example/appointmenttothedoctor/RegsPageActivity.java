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

/// Окно регистрация
public class RegsPageActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    // [START declare_auth]
    private FirebaseAuth mAuth;

    /// Поле EditText Email
    EditText emailEditText;
    /// Поле EditText Пароль
    EditText passEditText;
    /// Поле EditText Повторите пароль
    EditText repeatPassEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regs_page);

        emailEditText = findViewById(R.id.editTextEmail);
        passEditText = findViewById(R.id.editTextPass);
        repeatPassEditText = findViewById(R.id.editTextRepeatPass);
}

/// Кнопка отправить
    public void onClickBtSend(View view) {
        Log.d("onClick", "Send");

        Log.d("emailEditText", emailEditText.getText().toString());
        Log.d("passEditText", passEditText.getText().toString());
        Log.d("passEditRepeatText", repeatPassEditText.getText().toString());

        checkEditText(emailEditText.getText().toString(), passEditText.getText().toString(), repeatPassEditText.getText().toString());
    }

    /// Кнопка назад
    public void onClickBtBack(View view) {
        Log.d("onClick", "Back");
        Intent intent = new Intent(RegsPageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

   public void onClickBtYouAlreadyHaveAnAccount(View view){
       Log.d("onClick", "YouAlreadyHaveAnAccount");
       Intent intent = new Intent(RegsPageActivity.this, AuthPageActivity.class);
       startActivity(intent);
       finish();
   }


    /// Проверка на наличия текста в EditText
   private void checkEditText(String email, String pass, String repeatPass){
       if (email.length() == 0) {
           Toast.makeText(RegsPageActivity.this, "Заполните поле Email!",
                   Toast.LENGTH_SHORT).show();
           emailEditText.setError("Заполните поле Email!");
       } else if (pass.length() == 0) {
           Toast.makeText(RegsPageActivity.this, "Заполните поле Пароль!",
                   Toast.LENGTH_SHORT).show();
           passEditText.setError("Заполните поле Пароль!");
       } else if (repeatPass.length() == 0) {
           Toast.makeText(RegsPageActivity.this, "Заполните поле Повторите пароль",
                   Toast.LENGTH_SHORT).show();
           repeatPassEditText.setError("Заполните поле Повторите пароль");
       } else if (!pass.equals(repeatPass)){
           Toast.makeText(RegsPageActivity.this, "Пароли не совпадают!",
                   Toast.LENGTH_SHORT).show();
           repeatPassEditText.setError("Пароли не совпадают!");
       } else {
           mAuth = FirebaseAuth.getInstance();
           createAccount(email,  pass);
       }
    }

    /// Создание учетной записи
    private void createAccount(String email, String pass) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegsPageActivity.this, "Учетная запись успешно создана!",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(user);
                            sendEmailVerification();

                            Log.d("onClick", "Enter");
                            Intent intent = new Intent(RegsPageActivity.this, MenuPageActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                           //  If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegsPageActivity.this, "Не удалось создать учетную запись! " + task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }

        private void sendEmailVerification() {
        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Email sent
                    }
                });
        // [END send_email_verification]
    }

    private void updateUI(FirebaseUser user) {

    }
}

