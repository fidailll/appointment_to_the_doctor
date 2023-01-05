package com.example.appointmenttothedoctor.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appointmenttothedoctor.R;

public class AppToTheDoctorPageActivity extends AppCompatActivity {

    private static final int STATIC_INTEGER_VALUE = 0;
    private static final Object INVISIBLE = 0;
    //EditText Специлизация
    EditText editText1;
    //EditText Специалист
    EditText editText2;
    //EditText Услугу
    EditText editText3;
    //EditText Дата приема
    EditText editText4;

    //TextView Выбирите специализацию
    TextView textView1;
    //TextView Выбирите специалиста
    TextView textView2;
    //TextView Выбирите услугу
    TextView textView3;
    //TextView Дата приема
    TextView textView4;


    String specialization;
    String specialist;
    String serviceName;
    long servicePrice;
    long id_spec;
    long id_specialist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_to_the_doctor_page);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);

       // editText1.setText("My text");
        textView2.setVisibility(View.INVISIBLE);
        editText2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        editText3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        editText4.setVisibility(View.INVISIBLE);

        editTextClick1();
        editTextClick2();
        editTextClick3();
        editTextClick4();

        //надпись в AppBar
        setTitle(R.string.select_a_date);

        ///вызов панели действий
        ActionBar actionBar = getSupportActionBar();
        /// показываем кнопку «Назад» на панели действий
        actionBar.setDisplayHomeAsUpEnabled(true);

    }



    private void editTextClick1(){
        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppToTheDoctorPageActivity.this, SpecilizationPageActivity.class);
                startActivityForResult(intent, STATIC_INTEGER_VALUE);
//                specialization = getIntent().getExtras().getString("specialization");
//                id_spec = getIntent().getExtras().getLong("id_spec");

            }
        });
    }

    private void editTextClick2(){
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppToTheDoctorPageActivity.this, SpecialistPageActivity.class);
                intent.putExtra("id_spec", id_spec);
                startActivityForResult(intent, STATIC_INTEGER_VALUE);
                // String specialist = getIntent().getExtras().getString("specialist");
                //editText2.setText(specialist);
            }
        });
    }

    private void editTextClick3(){
        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppToTheDoctorPageActivity.this, ServicePageActivity.class);
                intent.putExtra("id_spec", id_spec);
                startActivityForResult(intent, STATIC_INTEGER_VALUE);
            }
        });
    }

    private void editTextClick4(){
        editText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Click EditText4");
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (STATIC_INTEGER_VALUE) : {
                if (resultCode == AppToTheDoctorPageActivity.RESULT_OK) {
                    //  String specialist = getIntent().getExtras().getString("specialist");
                    specialization = data.getExtras().getString("specialization");
                    id_spec = data.getExtras().getLong("id_spec");
                    specialist = data.getExtras().getString("specialist");
                    id_specialist = data.getExtras().getLong("id_specialist");
                    serviceName = data.getExtras().getString("service");
                    if (specialization != null) {
                        editText1.setText(specialization);
                        textView2.setVisibility(View.VISIBLE);
                        editText2.setVisibility(View.VISIBLE);


                        specialist = null;
                        serviceName = null;
                        editText2.setText(null);
                        editText3.setText(null);
                        textView3.setVisibility(View.INVISIBLE);
                        editText3.setVisibility(View.INVISIBLE);
                        textView4.setVisibility(View.INVISIBLE);
                        editText4.setVisibility(View.INVISIBLE);
                    }

                    if (specialist != null) {
                        editText2.setText(specialist);

                        serviceName = null;
                        editText3.setText(null);
                        textView3.setVisibility(View.VISIBLE);
                        editText3.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.INVISIBLE);
                        editText4.setVisibility(View.INVISIBLE);
                    }

                    if (serviceName != null) {
                        editText3.setText(serviceName);

                        textView4.setVisibility(View.VISIBLE);
                        editText4.setVisibility(View.VISIBLE);
                    }
                }

                break;
            }
        }
    }
}

