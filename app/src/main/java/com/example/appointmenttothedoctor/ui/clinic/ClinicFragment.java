package com.example.appointmenttothedoctor.ui.clinic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appointmenttothedoctor.databinding.FragmentClinicBinding;
import com.example.appointmenttothedoctor.databinding.FragmentHistoryBinding;
import com.example.appointmenttothedoctor.ui.history.HistoryViewModel;

public class ClinicFragment extends Fragment {

    private FragmentClinicBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ClinicViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ClinicViewModel.class);

        binding = FragmentClinicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
