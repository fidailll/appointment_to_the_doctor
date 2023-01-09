package com.example.appointmenttothedoctor.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.appointmenttothedoctor.AwesomeService;
import com.example.appointmenttothedoctor.AwesomeServiceAdapter;
import com.example.appointmenttothedoctor.AwesomeSpecialist;
import com.example.appointmenttothedoctor.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ServicePageActivity extends AppCompatActivity {
    private ListView serviceListView;
    private AwesomeServiceAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference serviceDatabaseReference;
    ChildEventListener serviceChildEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_page);

        Bundle extras = getIntent().getExtras();

        long id_specialist = extras.getLong("id_specialist");
        long id_spec = extras.getLong("id_spec");

        serviceListView = findViewById(R.id.serviceListView);

        List<AwesomeService> awesomeServices = new ArrayList<>();
        adapter = new AwesomeServiceAdapter(this, R.layout.service_item, awesomeServices);

        serviceListView.setAdapter(adapter);
        //надпись в AppBar
        setTitle(R.string.services);
        database = FirebaseDatabase.getInstance("https://appointment-to-the-docto-129cb-default-rtdb.europe-west1.firebasedatabase.app/");
        serviceDatabaseReference = database.getReference().child("specialization").child(String.valueOf(id_spec)).child("specialist").child(String.valueOf(id_specialist)).child("service");

        ///вызов панели действий
        ActionBar actionBar = getSupportActionBar();
        /// показываем кнопку «Назад» на панели действий
        actionBar.setDisplayHomeAsUpEnabled(true);

        serviceChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //  System.out.println(snapshot.child("image").toString());
                AwesomeService service = snapshot.getValue(AwesomeService.class);
                //  System.out.println(specialist.toString());
                    adapter.add(service);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        serviceDatabaseReference.addChildEventListener(serviceChildEventListener);

        serviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onClick", "Service");
                // Intent intent = new Intent(SpecialistPageActivity.this, DatePageActivity.class);
                Intent intent = new Intent();
                // intent.putExtra("specialization", specialization);
                intent.putExtra("service", adapter.getItem(position).getName());
                setResult(AppToTheDoctorPageActivity.RESULT_OK, intent);
                finish();
//                startActivity(intent);
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