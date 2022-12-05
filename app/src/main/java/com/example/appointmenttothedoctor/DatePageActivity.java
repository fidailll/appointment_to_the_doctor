package com.example.appointmenttothedoctor;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DatePageActivity extends AppCompatActivity {
    private ListView dateListView;
    private AwesomeServicesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_page);
        String specialization = (String) getIntent().getSerializableExtra("specialization");
        String specialist = (String) getIntent().getSerializableExtra("specialist");

        dateListView = findViewById(R.id.dateListView);

        List<AwesomeServices> awesomeServices = new ArrayList<>();
        adapter = new AwesomeServicesAdapter(this, R.layout.specilization_item, awesomeServices);

        //  adapter.add({"name" : "1 декабря"});

        //надпись в AppBar
        setTitle(R.string.select_a_date);

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