package com.example.appointmenttothedoctor.ui.profile;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<String> user;
   // private final MutableLiveData<String> verified;

    public ProfileViewModel() {
        user = new MutableLiveData<>();
   //     verified = new MutableLiveData<>();
      //  mText.setValue("This is notifications fragment");
        getUserProfile();
    }

    public LiveData<String> getText() {
        return user;
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

           // this.verified.setValue("zfdsfsdf");

           // String uid = user.getUid();
        }
        // [END get_user_profile]
    }
}