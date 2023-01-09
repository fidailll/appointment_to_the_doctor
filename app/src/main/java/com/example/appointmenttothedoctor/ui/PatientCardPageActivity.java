package com.example.appointmenttothedoctor.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appointmenttothedoctor.R;
import com.squareup.picasso.Picasso;

public class PatientCardPageActivity extends AppCompatActivity {

    TextView userValue;
    TextView emailValue;
    TextView numberValue;
    ImageView imageView;

    String id;
    String userName;
    String email;
    String image;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_card_page);

        Bundle extras = getIntent().getExtras();
        image = "https://firebasestorage.googleapis.com/v0/b/appointment-to-the-docto-129cb.appspot.com/o/image%2F11139.jpg?alt=media&token=043e3934-93bd-45b0-86ed-099165a98089";

        userValue = findViewById(R.id.userValue);
        emailValue = findViewById(R.id.emailValue);
        numberValue = findViewById(R.id.numberValue);

        imageView = findViewById(R.id.avatarImage1);

        id = extras.getString("id");
        userName = extras.getString("userName");
        email = extras.getString("email");
        phone = extras.getString("phone");

        userValue.setText(userName);
        emailValue.setText(email);
        numberValue.setText(phone);


        Picasso.get().load(image).into(imageView);

        //надпись в AppBar
        setTitle(R.string.patient_card);

        ///вызов панели действий
        ActionBar actionBar = getSupportActionBar();
        /// показываем кнопку «Назад» на панели действий
        actionBar.setDisplayHomeAsUpEnabled(true);
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