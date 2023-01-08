package com.example.appointmenttothedoctor;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ApplicationAdapter extends ArrayAdapter<App> {

    public ApplicationAdapter(@NonNull Context context, int resource, List<App> application) {
        super(context, resource, application);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.application_item, parent, false);
        }
        App application = getItem(position);

        TextView dateValue = (TextView) convertView.findViewById(R.id.dateValue);
        TextView doctorValue = (TextView) convertView.findViewById(R.id.doctorValue);
        TextView specialization = (TextView) convertView.findViewById(R.id.specialization);

        dateValue.setText(application.getDate());
        doctorValue.setText(application.getDoctor());
        specialization.setText(application.getSpecialization());

        return convertView;
    }
}
