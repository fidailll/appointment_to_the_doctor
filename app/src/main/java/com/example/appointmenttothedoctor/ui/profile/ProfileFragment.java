package com.example.appointmenttothedoctor.ui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appointmenttothedoctor.R;
import com.example.appointmenttothedoctor.databinding.FragmentProfileBinding;
import com.example.appointmenttothedoctor.ui.MainActivity;
import com.example.appointmenttothedoctor.ui.MenuPageActivity;
import com.example.appointmenttothedoctor.ui.RegsPageActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    //TextView text;
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

//        EmailViewModel emailViewModel =
//                new ViewModelProvider(this).get(EmailViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.userName;
        profileViewModel.getUser().observe(getViewLifecycleOwner(), textView::setText);

       final TextView textView1 = binding.email;
        profileViewModel.getEmail().observe(getViewLifecycleOwner(), textView1::setText);

        final TextView textView2 = binding.emailVerified;
        profileViewModel.getEmailVerified().observe(getViewLifecycleOwner(), textView2::setText);

        final ImageView imageView = binding.avatarImage;

        final TextView textView3 = binding.user;
        textView3.setText(R.string.user);

        final TextView textView4 = binding.mail;
        textView4.setText(R.string.mail);


        profileViewModel.getImage().observe(
                 getViewLifecycleOwner(), new Observer<String>() {
                     @Override
                     public void onChanged(@Nullable String value) {
                         Picasso.get().load(value).into(imageView);
                     }
                 });

        final Button btResend = binding.btResend;

        profileViewModel.getBtResend().observe(
                getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean value) {
                        if(value) {
                            btResend.setVisibility(View.VISIBLE);
                            btResend.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    sendEmailVerificationWithContinueUrl();
                                }
                            });
                        } else {
                            btResend.setVisibility(View.INVISIBLE);
                        }
                    }
                });

//        final ImageView image = binding.avatarImage;
//        profileViewModel.getTextImage().observe(getViewLifecycleOwner());
        return root;
        //text.setText();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onClickBtResend(View view) {
        //  Log.d("onClick", "AppToTheDoctorPageActivity");
        // Intent intent = new Intent(MenuPageActivity.this, AppToTheDoctorPageActivity.class);
        // startActivity(intent);
    }

    // Метод отправки письма для потверждения аккаунта
    public void sendEmailVerificationWithContinueUrl() {
        // [START send_email_verification_with_continue_url]
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Firebase", "Email sent.");
                            Toast.makeText(getActivity(), "Письмо подтверждения отправлена на почтовый ящик!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("Firebase", "Email not sent.");
                            Toast.makeText(getActivity(), "Письмо не отправлено!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}