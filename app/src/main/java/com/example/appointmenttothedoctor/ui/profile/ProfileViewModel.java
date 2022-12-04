package com.example.appointmenttothedoctor.ui.profile;

import android.net.Uri;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appointmenttothedoctor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<String> user;
    private final MutableLiveData<String> verified;


    public ProfileViewModel() {
        user = new MutableLiveData<>();
        verified = new MutableLiveData<>();
      //  mText.setValue("This is notifications fragment");
//        this.verified.setValue("zfdsfsdf");
        getUserProfile();

    }

    public LiveData<String> getText() {
        return user;
    }
    public LiveData<String> getText1() {
        return verified;
    }

    public void getUserProfile() {
        // [START get_user_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            // String name = user.getDisplayName();
            String email = user.getEmail();

            this.user.setValue("Пользователь: " + email);

           // Uri photoUrl = user.getPhotoUrl();
            boolean emailVerified = user.isEmailVerified();
            //text.setText("This is notifications fragment");
           // String uid = user.getUid();
        }
        // [END get_user_profile]


    }


}