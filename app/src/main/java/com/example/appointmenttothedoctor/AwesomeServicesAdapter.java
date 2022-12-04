package com.example.appointmenttothedoctor;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AwesomeServicesAdapter extends ArrayAdapter<AwesomeServices> {

    public AwesomeServicesAdapter(Context context, int resource, List<AwesomeServices> services) {
        super(context, resource, services);

    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        if(convertView == null){
           convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.specilization_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.itemTextView);

        AwesomeServices service = getItem(position);
      //  System.out.println(service.getSpecList());

        textView.setText(service.getService());

        return convertView;


    }


}
