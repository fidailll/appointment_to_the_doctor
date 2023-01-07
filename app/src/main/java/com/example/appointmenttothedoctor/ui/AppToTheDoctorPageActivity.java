package com.example.appointmenttothedoctor.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.appointmenttothedoctor.App;
import com.example.appointmenttothedoctor.R;
import com.example.appointmenttothedoctor.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppToTheDoctorPageActivity extends AppCompatActivity {

    private static final int STATIC_INTEGER_VALUE = 0;
    private static final Object INVISIBLE = 0;
    private static final String TAG = "Firebase";
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

    Button button1;

    Calendar dateAndTime = Calendar.getInstance();
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    private FirebaseAuth mAuth;

    String specialization;
    String specialist;
    String serviceName;
    String patientName;
    String id;
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
        Bundle extras = getIntent().getExtras();

        patientName = extras.getString("patient_name");
        id = extras.getString("id");


        button1 = findViewById(R.id.button1);

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

        button1.setVisibility(View.INVISIBLE);

        editTextClick1();
        editTextClick2();
        editTextClick3();
        editTextClick4();


        //надпись в AppBar
        setTitle(R.string.app_name);

        ///вызов панели действий
        ActionBar actionBar = getSupportActionBar();
        /// показываем кнопку «Назад» на панели действий
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


// метод который открывает SpecilizationPageActivity
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

    // метод который открывает SpecialistPageActivity
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

    // метод который открывает ServicePageActivity
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

    // метод который Открывает расписание
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

// метод который получает с других Activity значения
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
                        editText4.setText(null);
                        textView3.setVisibility(View.INVISIBLE);
                        editText3.setVisibility(View.INVISIBLE);
                        textView4.setVisibility(View.INVISIBLE);
                        editText4.setVisibility(View.INVISIBLE);
                        button1.setVisibility(View.INVISIBLE);
                    }

                    if (specialist != null) {
                        editText2.setText(specialist);

                        serviceName = null;
                        editText3.setText(null);
                        editText4.setText(null);
                        textView3.setVisibility(View.VISIBLE);
                        editText3.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.INVISIBLE);
                        editText4.setVisibility(View.INVISIBLE);
                        button1.setVisibility(View.INVISIBLE);
                    }

                    if (serviceName != null) {
                        editText3.setText(serviceName);

                        editText4.setText(null);
                        textView4.setVisibility(View.VISIBLE);
                        editText4.setVisibility(View.VISIBLE);
                        button1.setVisibility(View.INVISIBLE);
                    }
                }

                button1.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                createApp();
                            }
                        }
                );
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
        button1.setVisibility(View.VISIBLE);
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
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
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
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        ChildEventListener usersChildEventListener;

        //User user = new User();
        //App app = new App();
       // app.setData(specialization, specialist, serviceName, editText4.getText().toString(), patientName, id);
        HashMap<String, Object> app = new HashMap();
        app.put("specializationApp", editText1.getText().toString());
        app.put("specialistApp", editText2.getText().toString());
        app.put("dateApp", editText4.getText().toString());
        app.put("serviceApp", editText3.getText().toString());
        app.put("patientName", patientName);
        app.put("patientId", id);
        List<HashMap<String, String>> listApp = new ArrayList();

       // listApp.add(app);


        database = FirebaseDatabase.getInstance("https://appointment-to-the-docto-129cb-default-rtdb.europe-west1.firebasedatabase.app/");
        usersDatabaseReference = database.getReference().child("entries");

//        usersChildEventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        };
//
//        usersDatabaseReference.addChildEventListener(usersChildEventListener);

        // Отправка данные в БД
        usersDatabaseReference.push().updateChildren(app).addOnSuccessListener(new OnSuccessListener<Void>() {
                    // Метод который вызывается если сообщение доставлено
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.w(TAG, "Data sent");
                        Intent intent = new Intent(AppToTheDoctorPageActivity.this, EntryCreatedSuccessfullyPageActivity.class);
                        intent.putExtra("specialization",editText1.getText().toString());
                        intent.putExtra("specialist",editText2.getText().toString());
                        intent.putExtra("serviceName",editText3.getText().toString());
                        intent.putExtra("date", editText4.getText().toString());
                        intent.putExtra("patienName", patientName);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {

                    // Метод который вызывается если сообщение не дошло до сервера или произошла ошибка
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Failed");
                        Toast.makeText(AppToTheDoctorPageActivity.this, "Не удалось создать заявку! " + e.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

//
//    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
//        public void
//    }
}

