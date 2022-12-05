package com.example.appointmenttothedoctor.ui.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appointmenttothedoctor.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<String> user;
    private final MutableLiveData<String> verified;
    private final MutableLiveData<String> image;

    DatabaseReference usersDatabaseReference;
    ChildEventListener usersChildEventListener;
    FirebaseDatabase database;


    public ProfileViewModel() {
        user = new MutableLiveData<>();
        verified = new MutableLiveData<>();
        image = new MutableLiveData<>();
        //  mText.setValue("This is notifications fragment");
//        this.verified.setValue("zfdsfsdf");
        getUserProfile();
    }

    public LiveData<String> getTextUser() {
        return user;
    }

    public LiveData<String> getTextVerified() {
        return verified;
    }

    public LiveData<String> getTextImage() {
        return image;
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
                    user.setValue("Пользователь: " + userName);
                    verified.setValue("Почта: " + userEmail);
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

//        // [START get_user_profile]
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//             String name = user.getDisplayName();
//            String email = user.getEmail();
//
//            this.user.setValue("Пользователь: " + email);
//
//            this.verified.setValue("sddfsdf" + name);
//
//           // Uri photoUrl = user.getPhotoUrl();
//            boolean emailVerified = user.isEmailVerified();
//            //text.setText("This is notifications fragment");
//           // String uid = user.getUid();
//        }
//        // [END get_user_profile]
    }
}