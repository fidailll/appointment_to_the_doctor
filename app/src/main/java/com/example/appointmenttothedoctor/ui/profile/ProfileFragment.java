package com.example.appointmenttothedoctor.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appointmenttothedoctor.User;
import com.example.appointmenttothedoctor.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

  //TextView text;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ProfileViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.emailAddress;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

//        final TextView textView1 = binding.emailVerified;
//        notificationsViewModel.getText1().observe(getViewLifecycleOwner(), textView1::setText);



        return root;
        //text.setText();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


//    public void getUserProfile() {
//        // [START get_user_profile]
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//
//            System.out.println(name);
//            System.out.println(email);
//            System.out.println(photoUrl);
//            // Check if user's email is verified
//            boolean emailVerified = user.isEmailVerified();
//
//            System.out.println(emailVerified);
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getIdToken() instead.
//            String uid = user.getUid();
//        }
//        // [END get_user_profile]
//    }
}