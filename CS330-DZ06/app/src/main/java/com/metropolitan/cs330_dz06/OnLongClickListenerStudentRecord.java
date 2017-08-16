package com.metropolitan.cs330_dz06;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mare on 6/11/17.
 */

public class OnLongClickListenerStudentRecord implements OnLongClickListener {

    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();
        System.out.print(id);
        final CharSequence[] items = {"Izmeni informacije", "Obriši studenta"};

        new AlertDialog.Builder(context).setTitle("Informacije o studentu")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));

                        } else if (item == 1) {
                            delete(Integer.parseInt(id));
                        }
                        dialog.dismiss();
                    }
                }).show();

        return false;
    }

    public void editRecord(final int studentId) {

        final TableControllerStudent tableControllerStudent = new TableControllerStudent(context);
        final ObjectStudent objectStudent = tableControllerStudent.readSingleRecord(studentId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

        final EditText editTextBrojIndeksa = (EditText) formElementsView.findViewById(R.id.editTextBrojIndeksa);
        final EditText editTextStudentsName = (EditText) formElementsView.findViewById(R.id.editTextStudentsName);
        final EditText editTextBrojBodova = (EditText) formElementsView.findViewById(R.id.editTextBrojPoena);

        editTextBrojIndeksa.setText(String.valueOf(objectStudent.getBroj_indeksa()));
        editTextStudentsName.setText(objectStudent.getIme());
        editTextBrojBodova.setText(String.valueOf(objectStudent.getBroj_bodova()));

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Izmeni informacije")
                .setPositiveButton("Snimi",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int brojIndeksa) {

                                ObjectStudent objectStudent = new ObjectStudent(Integer.parseInt(editTextBrojIndeksa.getText().toString()),
                                        editTextStudentsName.getText().toString(), Integer.parseInt(editTextBrojBodova.getText().toString()));

                                boolean updateSuccessful = tableControllerStudent.update(objectStudent, studentId);

                                if (updateSuccessful) {
                                    Toast.makeText(context, "Uspešno izmenjene informacije", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Došlo je do greške! \nInformacije nisu izmenjene", Toast.LENGTH_SHORT).show();
                                }

                                ((MainActivity) context).countRecords();
                                ((MainActivity) context).readRecords();
                                dialog.cancel();
                            }
                        }).show();
    }

    public void delete(final int studentID) {
        final TableControllerStudent tableControllerStudent = new TableControllerStudent(context);
        boolean delete = tableControllerStudent.deleteContact(studentID);
        if (delete) {
            Toast.makeText(context, "Informacije o studentu su obrisane", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Došlo je do greške! \nNje moguće obrisati informacije", Toast.LENGTH_SHORT).show();
        }
        ((MainActivity) context).countRecords();
        ((MainActivity) context).readRecords();
    }

}
