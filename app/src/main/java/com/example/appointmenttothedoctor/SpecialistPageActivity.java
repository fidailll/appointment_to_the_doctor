package com.example.appointmenttothedoctor;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SpecialistPageActivity extends AppCompatActivity {
    private ListView specialistListView;
    private AwesomeSpecialistAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference specialistDatabaseReference;
    ChildEventListener specialistChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_page);

        specialistListView = findViewById(R.id.specialistListView);
        List<AwesomeSpecialist> awesomeSpecialists = new ArrayList<>();
        adapter = new AwesomeSpecialistAdapter(this, R.layout.specialist_item, awesomeSpecialists);

        specialistListView.setAdapter(adapter);
        //надпись в AppBar
        setTitle(R.string.specialist);

        database = FirebaseDatabase.getInstance("https://appointment-to-the-docto-129cb-default-rtdb.europe-west1.firebasedatabase.app/");
        specialistDatabaseReference = database.getReference().child("specialist").child("test");
        ///вызов панели действий
        ActionBar actionBar = getSupportActionBar();
        /// показываем кнопку «Назад» на панели действий
        actionBar.setDisplayHomeAsUpEnabled(true);

        specialistChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println(snapshot.child("image").toString());
                AwesomeSpecialist specialist = snapshot.getValue(AwesomeSpecialist.class);
              //  System.out.println(specialist.toString());
                adapter.add(specialist);
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

        specialistDatabaseReference.addChildEventListener(specialistChildEventListener);
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