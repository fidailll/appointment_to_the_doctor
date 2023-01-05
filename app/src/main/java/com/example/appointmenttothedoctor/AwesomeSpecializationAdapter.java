package com.example.appointmenttothedoctor;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AwesomeSpecializationAdapter extends ArrayAdapter<AwesomeSpecialization> {

    public AwesomeSpecializationAdapter(Context context, int resource, List<AwesomeSpecialization> services) {
        super(context, resource, services);
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        if(convertView == null){
           convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.specilization_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.itemTextView);

        AwesomeSpecialization specialization = getItem(position);

        textView.setText(specialization.getService());

        return convertView;

    }

}
