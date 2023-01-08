package com.example.appointmenttothedoctor.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.appointmenttothedoctor.CallDialogFragment;
import com.example.appointmenttothedoctor.R;
import com.example.appointmenttothedoctor.User;
import com.example.appointmenttothedoctor.databinding.ActivityChatPageBinding;
import com.example.appointmenttothedoctor.databinding.ActivityMenuPageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuPageActivity extends AppCompatActivity {

    private ActivityMenuPageBinding binding;

    DatabaseReference usersDatabaseReference;
    ChildEventListener usersChildEventListener;
    FirebaseDatabase database;

    String userName;
    String id;
    String phone;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
               // R.id.navigation_history,
                R.id.navigation_profile,
                R.id.navigation_clinic)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_menu_page);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        ///вызов панели действий
        ActionBar actionBar = getSupportActionBar();



        database = FirebaseDatabase.getInstance("https://appointment-to-the-docto-129cb-default-rtdb.europe-west1.firebasedatabase.app/");
        usersDatabaseReference = database.getReference().child("users");


        /// Выгрузка профиля
        usersChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                     userName = user.getName();
                     id = user.getId();
                     phone = user.getPhone();
                     email = user.getEmail();
                    System.out.println(userName);
                    System.out.println(id);
                    System.out.println(phone);
                    System.out.println(email);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_menu, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more_vert:  {
                onClickSignOut();
                return true;
            }
            case R.id.edit:  {
                onClickEditProfile();
                return true;
            }
            case R.id.call:  {
             //  onClickCall();
                onCreateDialog();
                System.out.println("sadhfhgsdafhds");
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCreateDialog() {
        FragmentManager manager = getSupportFragmentManager();
        CallDialogFragment myDialogFragment = new CallDialogFragment();

        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }



    private void onClickSignOut(){
        FirebaseAuth.getInstance().signOut();
        Log.d("onClick", "Sign out");
        Intent intent = new Intent(MenuPageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void onClickEditProfile(){
        Log.d("onClick", "Edit Profile");
        Intent intent = new Intent(MenuPageActivity.this, EditProfilePageActivity.class);
        startActivity(intent);
    }

    public void onClickBtAppToTheDoctor(View view) {
        Log.d("onClick", "AppToTheDoctorPageActivity");
        Intent intent = new Intent(MenuPageActivity.this, AppToTheDoctorPageActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("patient_name", userName);
        startActivity(intent);
    }

    public void onClickBtSignUp(View view) {
        Log.d("onClick", "AppToTheDoctorPageActivity");
        Intent intent = new Intent(MenuPageActivity.this, AppToTheDoctorPageActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("patient_name", userName);
        startActivity(intent);
    }

    public void onClickBtPatientCard(View view) {
        Log.d("onClick", "PatientCardPageActivity");
        Intent intent = new Intent(MenuPageActivity.this, PatientCardPageActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("userName", userName);
        intent.putExtra("email", email);
        intent.putExtra("phone", phone);
        startActivity(intent);
    }


    private void onClickCall(){
        Log.d("onClick", "Call");
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:89174152916"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}