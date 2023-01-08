package com.example.appointmenttothedoctor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appointmenttothedoctor.AwesomeSpecialist;
import com.example.appointmenttothedoctor.AwesomeSpecialistAdapter;
import com.example.appointmenttothedoctor.R;
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

        Bundle extras = getIntent().getExtras();


     // String specialization = extras.getString("specialization");
      long id_spec = extras.getLong("id_spec");

        specialistListView = findViewById(R.id.specialistListView);

        List<AwesomeSpecialist> awesomeSpecialists = new ArrayList<>();
        adapter = new AwesomeSpecialistAdapter(this, R.layout.specialist_item, awesomeSpecialists);

        specialistListView.setAdapter(adapter);
        //надпись в AppBar
        setTitle(R.string.specialist);

        database = FirebaseDatabase.getInstance("https://appointment-to-the-docto-129cb-default-rtdb.europe-west1.firebasedatabase.app/");
        specialistDatabaseReference = database.getReference().child("specialization").child(String.valueOf(id_spec)).child("specialist");

        ///вызов панели действий
        ActionBar actionBar = getSupportActionBar();
        /// показываем кнопку «Назад» на панели действий
        actionBar.setDisplayHomeAsUpEnabled(true);

        specialistChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
              //  System.out.println(snapshot.child("image").toString());
                AwesomeSpecialist specialist = snapshot.getValue(AwesomeSpecialist.class);
              //  System.out.println(specialist.toString());
                if(previousChildName != null){
                    adapter.add(specialist);
                }

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

        specialistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onClick", "Specialist");
               // Intent intent = new Intent(SpecialistPageActivity.this, DatePageActivity.class);
                Intent intent = new Intent();
               // intent.putExtra("specialization", specialization);

                String specialist =  adapter.getItem(position).getName();
                long id_specialist = adapter.getItemId(position);

                intent.putExtra("specialist", specialist);
                intent.putExtra("id_specialist", id_specialist);
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