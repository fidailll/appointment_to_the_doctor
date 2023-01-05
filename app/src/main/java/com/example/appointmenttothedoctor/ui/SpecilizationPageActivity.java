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

import com.example.appointmenttothedoctor.AwesomeServices;
import com.example.appointmenttothedoctor.AwesomeServicesAdapter;
import com.example.appointmenttothedoctor.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SpecilizationPageActivity extends AppCompatActivity {
    private ListView specilizationListView;
    private AwesomeServicesAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference servicesDatabaseReference;
    ChildEventListener servicesChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specilization_page);

        specilizationListView = findViewById(R.id.specilizationListView);
        List<AwesomeServices> awesomeServices = new ArrayList<>();
        adapter = new AwesomeServicesAdapter(this, R.layout.specilization_item, awesomeServices);

        specilizationListView.setAdapter(adapter);

        //надпись в AppBar
        setTitle(R.string.specialization);

        database = FirebaseDatabase.getInstance("https://appointment-to-the-docto-129cb-default-rtdb.europe-west1.firebasedatabase.app/");
        servicesDatabaseReference = database.getReference().child("specialization");

        ///вызов панели действий
        ActionBar actionBar = getSupportActionBar();
        /// показываем кнопку «Назад» на панели действий
        actionBar.setDisplayHomeAsUpEnabled(true);

        servicesChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                 AwesomeServices service = snapshot.getValue(AwesomeServices.class);
                //service.getService();
               // System.out.println(snapshot.child("specialist").toString());
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

        servicesDatabaseReference.addChildEventListener(servicesChildEventListener);

        specilizationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onClick", "Specialization");
                Intent intent = new Intent();

                String spec =  adapter.getItem(position).getService();
                long id_spec = adapter.getItemId(position);

                Log.d("spec", spec);
                Log.d("id_spec", String.valueOf(id_spec));

                intent.putExtra("specialization", spec);
                intent.putExtra("id_spec", id_spec);
                setResult(AppToTheDoctorPageActivity.RESULT_OK, intent);
                finish();
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

//    public void onClickServices(View view) {
//        Log.d("onClick", "Specialization");
//        //System.out.println(adapter.getItemId());
//        Intent intent = new Intent(SpecilizationPageActivity.this, SpecialistPageActivity.class);
//        intent.putExtra("specialization", servicesChildEventListener.toString());
//        startActivity(intent);
//    }

}