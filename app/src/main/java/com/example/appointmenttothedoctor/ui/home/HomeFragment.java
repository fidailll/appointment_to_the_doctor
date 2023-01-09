package com.example.appointmenttothedoctor.ui.home;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.appointmenttothedoctor.App;
import com.example.appointmenttothedoctor.AppDialogFragment;
import com.example.appointmenttothedoctor.ApplicationAdapter;
import com.example.appointmenttothedoctor.CallDialogFragment;
import com.example.appointmenttothedoctor.R;
import com.example.appointmenttothedoctor.User;
import com.example.appointmenttothedoctor.databinding.FragmentHomeBinding;
import com.example.appointmenttothedoctor.ui.AppToTheDoctorPageActivity;
import com.example.appointmenttothedoctor.ui.EntryCreatedSuccessfullyPageActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ApplicationAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference applicationDatabaseReference;
    ChildEventListener applicationChildEventListener;

    ListView appListView;
    CardView cardView;
    //Button button;

    List<App> application = new ArrayList<>();
    List<String> listItem= new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        database = FirebaseDatabase.getInstance("https://appointment-to-the-docto-129cb-default-rtdb.europe-west1.firebasedatabase.app/");
        applicationDatabaseReference = database.getReference().child("entries");

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        adapter = new ApplicationAdapter(getContext(), R.layout.application_item, application);

        //button = binding.btSignUp;
        appListView =  binding.appListView;
         cardView = binding.appCardView;

        View root = binding.getRoot();

      //  selectedClickItem();
        getEntries();
        clickItem();


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

       return root;


    }

    private void getEntries(){
        applicationChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                App app = snapshot.getValue(App.class);
                if (app.getPatientId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    if(previousChildName != null){
                        adapter.add(app);
                        listItem.add(previousChildName);
                        System.out.println(previousChildName);

                    }
                    if(adapter.isEmpty()){
                        appListView.setVisibility(View.INVISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }else {
                        cardView.setVisibility(View.INVISIBLE);
                        appListView.setVisibility(View.VISIBLE);
                        appListView.setAdapter(adapter);
                    }
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
        applicationDatabaseReference.addChildEventListener(applicationChildEventListener);
    }

//    private void  selectedClickItem(){
//        appListView.setOnLongClickListener( new AdapterView.OnLongClickListener() {
//
//            @Override
//            public boolean onLongClick(View v) {
//                System.out.println("dfsfdsfdsf");
//                return false;
//            }
//
//
//        });
//    }



    private  void clickItem(){

        appListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
  //               onCreateDialog();
                String child = listItem.get((int) adapter.getItemId(position) );
                applicationDatabaseReference.child(child).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            // Метод который вызывается если сообщение доставлено
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Запись удалена! " ,
                                        Toast.LENGTH_SHORT).show();
                                listItem.remove(adapter.getItem(position));
                                adapter.remove(adapter.getItem(position));
                                if(adapter.isEmpty()){
                                    appListView.setVisibility(View.INVISIBLE);
                                    cardView.setVisibility(View.VISIBLE);
                                }else {
                                    cardView.setVisibility(View.INVISIBLE);
                                    appListView.setVisibility(View.VISIBLE);
                                    appListView.setAdapter(adapter);
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {

                            // Метод который вызывается если сообщение не дошло до сервера или произошла ошибка
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Log.w(TAG, "Failed");
                                Toast.makeText(getActivity(), "Не удалось создать заявку! " + e.toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public void onCreateDialog() {
        FragmentManager manager =  getActivity().getSupportFragmentManager();
        AppDialogFragment myDialogFragment = new AppDialogFragment();

        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}