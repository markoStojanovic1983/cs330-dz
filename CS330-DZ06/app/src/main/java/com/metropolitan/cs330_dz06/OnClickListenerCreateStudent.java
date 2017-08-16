package com.metropolitan.cs330_dz06;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mare on 6/11/17.
 */

public class OnClickListenerCreateStudent implements View.OnClickListener {
    @Override
    public void onClick(View view) {

        final Context context = view.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

        final EditText editTextBrojIndeksa = (EditText) formElementsView.findViewById(R.id.editTextBrojIndeksa);
        final EditText editTextStudentsName = (EditText) formElementsView.findViewById(R.id.editTextStudentsName);
        final EditText editTextBrojPoena = (EditText) formElementsView.findViewById(R.id.editTextBrojPoena);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Kreiraj Studenta")
                .setPositiveButton("Dodaj",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                int brojIndeksa = Integer.parseInt(editTextBrojIndeksa.getText().toString());
                                String ime = editTextStudentsName.getText().toString();
                                int brojBodova = Integer.parseInt(editTextBrojPoena.getText().toString());

                                ObjectStudent objectStudent = new ObjectStudent(brojIndeksa, ime, brojBodova);

                                boolean createSuccessful = new TableControllerStudent(context).create(objectStudent);

                                if (createSuccessful) {
                                    Toast.makeText(context, "Uspešan unos informacija o studentu.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Došlo je do greške! \nInformacije o studentu nisu snimljene.", Toast.LENGTH_SHORT).show();
                                }
                                ((MainActivity) context).readRecords();
                                ((MainActivity) context).countRecords();
                                dialog.cancel();
                            }

                        }).show();
    }
}



