package com.example.appointmenttothedoctor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AppDialogFragment  extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Запись")
                //.setIcon(android.R.drawable.ic_dialog_alert)
                .setView(R.layout.app_dialog).setMessage("Вы хотите отменить запись?").setMessage("Вы хотите отменить зываыаывапись?")
//                setView(R.id.patientValue).
//                setView(R.id.dateValue).
//                setView(R.id.serviceValue).
//                setView(R.id.dateValue)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Нет", null)
                .create();
    }
}
