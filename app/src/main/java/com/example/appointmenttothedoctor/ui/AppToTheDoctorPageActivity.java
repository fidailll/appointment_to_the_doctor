package com.example.appointmenttothedoctor.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.appointmenttothedoctor.R;
import com.example.appointmenttothedoctor.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    Calendar dateAndTime = Calendar.getInstance();
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    private FirebaseAuth mAuth;

    String specialization;
    String specialist;
    String serviceName;
    long servicePrice;
    long id_spec;
    long id_specialist;

    /// Подключение к Storage в firebase
    FirebaseDatabase database;
    DatabaseReference usersDatabaseReference;

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
                setTime(v);

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



    // установка начальных даты и времени
    private void setInitialDateTime() {
        editText4.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME
                       ));
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
      datePickerDialog =  new DatePickerDialog(AppToTheDoctorPageActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(dateAndTime.getTimeInMillis());
        datePickerDialog.show();
        // setInitialDateTime();

    }
//
    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        timePickerDialog = new TimePickerDialog(AppToTheDoctorPageActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true);

        timePickerDialog.show();
        setDate(v);

    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    private void createApp() {

        final FirebaseUser user = mAuth.getCurrentUser();
        User profile

        // user.set
    }

//
//    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
//        public void
//    }
}

