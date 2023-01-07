package com.example.appointmenttothedoctor.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appointmenttothedoctor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PassRecoveryActivity extends AppCompatActivity {
    private static final String TAG = "SendAPasswordResetEmail";

    EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_recovery);

        emailEditText = findViewById(R.id.enterYourEmailEditText);
        //надпись в AppBar
        setTitle(R.string.password_recovery);

        ///вызов панели действий
        ActionBar actionBar = getSupportActionBar();
        /// показываем кнопку «Назад» на панели действий
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickBtReestablish(View view){
        Log.d("emailEditText", emailEditText.getText().toString());

        checkEditText(emailEditText.getText().toString());
    }

//    public void onClickBtAuthPage(View view){
//        Log.d("onClick", "Auth");
//        Intent intent = new Intent(PassRecoveryActivity.this, AuthPageActivity.class);
//        startActivity(intent);
//        finish();
//    }

    /// Проверка на наличия текста в EditText
    private void checkEditText(String email){
        if (email.length() == 0) {
            Toast.makeText(PassRecoveryActivity.this, "Заполните поле Email!",
                    Toast.LENGTH_SHORT).show();
            emailEditText.setError("Заполните поле Email!");
        } else {
            sendAPasswordResetEmail(email);
        }
    }

    /// Отправить электронное письмо для сброса пароля
    private void sendAPasswordResetEmail(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        //String emailAddress = "user@example.com";

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PassRecoveryActivity.this, "Письмо отправлено на ваш электронный ящик!",
                                    Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Email sent.");
                        } else {
                            Toast.makeText(PassRecoveryActivity.this, "Не получилось отправить письмо!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // это событие активирует обратную
    // функция для кнопки при нажатии
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}