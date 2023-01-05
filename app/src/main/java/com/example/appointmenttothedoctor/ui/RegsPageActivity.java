package com.example.appointmenttothedoctor.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appointmenttothedoctor.R;
import com.example.appointmenttothedoctor.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/// Окно регистрация
public class RegsPageActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    // [START declare_auth]
    private FirebaseAuth mAuth;

    /// Поле EditText Name
    EditText nameEditText;
    /// Поле EditText Email
    EditText emailEditText;
    /// Поле EditText Phone
    EditText phoneEditText;
    /// Поле EditText Пароль
    EditText passEditText;
    /// Поле EditText Повторите пароль
    EditText repeatPassEditText;

    /// Подключение к Storage в firebase
    FirebaseDatabase database;
    DatabaseReference usersDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regs_page);


                nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passEditText = findViewById(R.id.passEditText);
        repeatPassEditText = findViewById(R.id.repeatPassEditText);
        phoneEditText = findViewById(R.id.phoneEditText);

        // инициализация Users в Storage Firebase
        database = FirebaseDatabase.getInstance("https://appointment-to-the-docto-129cb-default-rtdb.europe-west1.firebasedatabase.app/");
        usersDatabaseReference = database.getReference().child("users");
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");
//        usersDatabaseReference.setValue("sdffd");
}

/// Кнопка отправить
    public void onClickBtSend(View view) {
        Log.d("onClick", "Send");

        Log.d("nameEditText", nameEditText.getText().toString());
        Log.d("emailEditText", emailEditText.getText().toString());
        Log.d("phoneEditText", passEditText.getText().toString());
        Log.d("passEditText", passEditText.getText().toString());
        Log.d("passEditRepeatText", repeatPassEditText.getText().toString());

        checkEditText(nameEditText.getText().toString(), emailEditText.getText().toString(),phoneEditText.getText().toString(),passEditText.getText().toString(), repeatPassEditText.getText().toString());
    }

    /// Кнопка назад
    public void onClickBtBack(View view) {
        Log.d("onClick", "Back");
        Intent intent = new Intent(RegsPageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    ///
   public void onClickBtYouAlreadyHaveAnAccount(View view){
       Log.d("onClick", "YouAlreadyHaveAnAccount");
       Intent intent = new Intent(RegsPageActivity.this, AuthPageActivity.class);
       startActivity(intent);
       finish();
   }


    /// Проверка на наличия текста в EditText
   private void checkEditText(String name, String email, String phone, String pass, String repeatPass){
       if (name.length() == 0) {
           Toast.makeText(RegsPageActivity.this, "Заполните поле ФИО!",
                   Toast.LENGTH_SHORT).show();
           nameEditText.setError("Заполните поле ФИО!");
       } else if (email.length() == 0) {
           Toast.makeText(RegsPageActivity.this, "Заполните поле Email!",
                   Toast.LENGTH_SHORT).show();
           emailEditText.setError("Заполните поле Email!");
       } else if (pass.length() == 0) {
           Toast.makeText(RegsPageActivity.this, "Заполните поле Пароль!",
                   Toast.LENGTH_SHORT).show();
           passEditText.setError("Заполните поле Пароль!");
       } else if (phone.length() < 10) {
           Toast.makeText(RegsPageActivity.this, "Заполните поле Номер телефона!",
                   Toast.LENGTH_SHORT).show();
           phoneEditText.setError("Заполните поле Номер телефона!");
       }
       else if (repeatPass.length() == 0) {
           Toast.makeText(RegsPageActivity.this, "Заполните поле Повторите пароль",
                   Toast.LENGTH_SHORT).show();
           repeatPassEditText.setError("Заполните поле Повторите пароль");
       } else if (!pass.equals(repeatPass)) {
           Toast.makeText(RegsPageActivity.this, "Пароли не совпадают!",
                   Toast.LENGTH_SHORT).show();
           repeatPassEditText.setError("Пароли не совпадают!");
       } else if (pass.length() < 6){
           Toast.makeText(RegsPageActivity.this, "Пароль должен содержать не менее 6 символов",
                   Toast.LENGTH_SHORT).show();
           passEditText.setError("Пароль должен содержать больше 6 символов");
       }
       else {
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
                            createUser(user);
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


    /// Создания User в Storage Firebase
    private void createUser(FirebaseUser firebaseUser) {
        User user = new User();

        user.setId(firebaseUser.getUid());
        user.setEmail(firebaseUser.getEmail());
        user.setName(nameEditText.getText().toString().trim());
        user.setPhone(phoneEditText.getText().toString().trim());

        ///Отправка в Storage
        usersDatabaseReference.push().setValue(user);
    }

    /// Отправка потвержающего письма на почтовый ящик
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

