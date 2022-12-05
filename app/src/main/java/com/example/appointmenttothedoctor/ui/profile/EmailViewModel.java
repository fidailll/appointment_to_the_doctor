package com.example.appointmenttothedoctor.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailViewModel extends ViewModel {

    private final MutableLiveData<String> verified;


    public EmailViewModel() {

        verified = new MutableLiveData<>();
      //  mText.setValue("This is notifications fragment");
//        this.verified.setValue("zfdsfsdf");
        getUserProfile();

    }


    public LiveData<String> getTextVerified() {
        return verified;
    }

    public void getUserProfile() {
        // [START get_user_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            // String name = user.getDisplayName();
            String email = user.getEmail();

           // this.user.setValue("Пользователь: " + email);

            this.verified.setValue("Пользователь: " + email);

           // Uri photoUrl = user.getPhotoUrl();
            boolean emailVerified = user.isEmailVerified();
            //text.setText("This is notifications fragment");
           // String uid = user.getUid();
        }
        // [END get_user_profile]
    }
}