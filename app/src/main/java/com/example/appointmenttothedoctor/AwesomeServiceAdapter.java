package com.example.appointmenttothedoctor;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AwesomeServiceAdapter  extends ArrayAdapter<AwesomeService> {

    public AwesomeServiceAdapter(Context context, int resource, List<AwesomeService> service) {
        super(context, resource, service);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.service_item, parent, false);
        }
        AwesomeService service = getItem(position);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.itemTextView1);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.itemTextView2);


        nameTextView.setText(service.getName());
        priceTextView.setText("Стоимость: " + service.getPrice() + " р");

        return convertView;
    }
}
