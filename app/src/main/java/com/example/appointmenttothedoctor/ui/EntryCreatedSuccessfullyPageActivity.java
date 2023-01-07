package com.example.appointmenttothedoctor.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appointmenttothedoctor.R;

public class EntryCreatedSuccessfullyPageActivity extends AppCompatActivity {

    TextView patientValue;
    TextView dateValue;
    TextView serviceValue;
    TextView doctorValue;

    Button btResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_created_successfully_page);
        Bundle extras = getIntent().getExtras();

        String specialization = extras.getString("specialization");
        String specialist = extras.getString("specialist");
        String serviceName = extras.getString("serviceName");
        String date = extras.getString("date");
        String patienName = extras.getString("patienName");
        //tring patientId = extras.getString("patientId");

        patientValue = findViewById(R.id.patientValue);
        dateValue = findViewById(R.id.dateValue);
        serviceValue = findViewById(R.id.serviceValue);
        doctorValue = findViewById(R.id.doctorValue);

        btResend = findViewById(R.id.btResend);

        patientValue.setText(patienName);
        dateValue.setText(date);
        serviceValue.setText(serviceName);
        doctorValue.setText(specialist);

        btClick();
    }

    public void btClick(){
        btResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}