package com.example.appointmenttothedoctor;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AwesomeSpecialistAdapter extends ArrayAdapter<AwesomeSpecialist> {



    public AwesomeSpecialistAdapter(Context context, int resource, List<AwesomeSpecialist> specialist) {
        super(context, resource, specialist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.specialist_item, parent, false);
        }
        AwesomeSpecialist specialist = getItem(position);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView experienceTextView = (TextView) convertView.findViewById(R.id.experienceTextView);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.avatarImageView);

        boolean isImage = specialist.getImage() == null;

        if(isImage){
            //Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/appointment-to-the-docto-129cb.appspot.com/o/image%2F8lsxM560HcQ.jpg?alt=media&token=3fb17c66-d548-47e5-8d55-f6bce381fad8").into(imageView);
        } else {
            Picasso.get().load(specialist.getImage()).into(imageView);
        }
       //   System.out.println(specialist.getName());

        nameTextView.setText(specialist.getName());
        experienceTextView.setText("Стаж: " + specialist.getExperience());

        return convertView;
    }

}
