package com.example.appointmenttothedoctor.ui;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appointmenttothedoctor.AwesomeSpecializationAdapter;
import com.example.appointmenttothedoctor.R;

public class DatePageActivity extends AppCompatActivity {
    private ListView dateListView;
    private AwesomeSpecializationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_page);
        Bundle extras = getIntent().getExtras();


        String specialization = extras.getString("specialization");

        String specialist = extras.getString("specialist");

       //Текущее время
        Calendar today = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        maxDate.set(Calendar.YEAR, Calendar.YEAR+2);

        DatePicker datePicker = this.findViewById(R.id.datePicker);
        // До сегодняшнего дня
        datePicker.setMinDate(today.getTimeInMillis());
       // datePicker.setMaxDate(maxDate.getTimeInMillis());



        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                // Отсчет месяцев начинается с нуля. Для отображения добавляем 1.
              Log.d("onClick", "Дата: " + view.getDayOfMonth() + "/" +
                      (view.getMonth() + 1) + "/" + view.getYear()) ;

                // альтернативная запись
                // dateTextView.setText("Дата: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        });


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