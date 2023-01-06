package com.example.appointmenttothedoctor.ui.profile;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appointmenttothedoctor.User;
import com.example.appointmenttothedoctor.ui.AppToTheDoctorPageActivity;
import com.example.appointmenttothedoctor.ui.MenuPageActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<String> user ;
    private final MutableLiveData<String> email;
    private final MutableLiveData<String> emailVerified;
    private final MutableLiveData<String> image;
    private final MutableLiveData<Boolean> btResend;

    DatabaseReference usersDatabaseReference;
    ChildEventListener usersChildEventListener;
    FirebaseDatabase database;


    public ProfileViewModel() {
        user = new MutableLiveData<>();
        email = new MutableLiveData<>();
        emailVerified = new MutableLiveData<>();
        image = new MutableLiveData<>();
        btResend = new MutableLiveData<>();
        getUserProfile();
    }

    public LiveData<String> getUser() {
        return user;
    }

    public LiveData<String> getEmail() {return email;}

    public LiveData<String> getEmailVerified() {return emailVerified;}

    public LiveData<String> getImage() {
        return image;
    }

    public LiveData<Boolean> getBtResend() {
        return btResend;
    }

    public void getUserProfile() {
        database = FirebaseDatabase.getInstance("https://appointment-to-the-docto-129cb-default-rtdb.europe-west1.firebasedatabase.app/");
        usersDatabaseReference = database.getReference().child("users");
        /// Выгрузка профиля
        usersChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    String userName = userProfile.getName();
                    String userEmail = userProfile.getEmail();
                    System.out.println(userName);
                    System.out.println(userEmail);
                    user.setValue(userName);
                    email.setValue(userEmail);
                    image.setValue("https://firebasestorage.googleapis.com/v0/b/appointment-to-the-docto-129cb.appspot.com/o/image%2Ff43e2f75e992c8a479785ef7adb6dc41.jpg?alt=media&token=7011987b-03ed-4cea-973f-023b6f3a73dc");
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };

        usersDatabaseReference.addChildEventListener(usersChildEventListener);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
           // Uri photoUrl = user.getPhotoUrl();
            boolean verified = user.isEmailVerified();
            Log.d("emailVerified", String.valueOf(verified));
            if(!verified) {
                emailVerified.setValue("Ваш аккаунт не верифицирован. Ответьте на письмо которое было выслано вам на ваш электронный почтовый ящик.");
                btResend.setValue(true);
            } else {
                btResend.setValue(false);
            }

        }

        // [END get_user_profile]
    }

}