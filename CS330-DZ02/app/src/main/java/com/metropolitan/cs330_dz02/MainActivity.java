package com.metropolitan.cs330_dz02;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    CharSequence[] items = {"FIT", "Fakultet za Menadžment", "Fakultet digitalnih umetnosti"};
    //	boolean[] itemsChecked = new boolean[items.length];
    String choosenFax = "FIT";

    ProgressDialog progressDialog;

    /**
     * Poziva se prvim definisanjem aktivnosti.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        showDialog(0);
    }

    public void onClick2(View v) {
        // ---prikazuje dijalog---
        dialog = ProgressDialog.show(this, "Nešto se dešava.", "Sačekajte...", true);
        new Thread(new Runnable() {
            public void run() {
                try {
                    // ---simulacija da nešto radi---
                    Thread.sleep(5000);
                    // ---odjavljuje dijalog---
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void onClick3(View v) {
        showDialog(1);
        progressDialog.setProgress(0);

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 15; i++) {
                    try {
                        // ---simulacija da nešto radi---
                        Thread.sleep(1000);
                        // ---osvežavanje dijaloga---
                        progressDialog.incrementProgressBy((int) (100 / 15));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    public String resolveLink() {
        if (choosenFax.equalsIgnoreCase("FIT")) {
            return "http://www.metropolitan.edu.rs/osnovne-studije/fakultet-informacionih-tehnologija/";
        } else if (choosenFax.equalsIgnoreCase("Fakultet za Menadžment")) {
            return "http://www.metropolitan.edu.rs/osnovne-studije/fakultet-za-menadzment/";
        } else if (choosenFax.equalsIgnoreCase("Fakultet digitalnih umetnosti")) {
            return "http://www.metropolitan.edu.rs/fakultet-digitalnih-umetnosti-2/";
        }
        return "https://www.google.rs/";
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("Dijalog sa malo teksta...")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent i = new Intent("android.intent.action.VIEW");
                                i.setData(Uri.parse(resolveLink()));
                                startActivity(i);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getBaseContext(), "Cancel je kliknut!", Toast.LENGTH_SHORT).show();
                            }
                        }).setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//							Toast.makeText(getBaseContext(), items[which] + (isChecked ? " checked!" : " unchecked!"),
//									Toast.LENGTH_SHORT).show();
                                choosenFax = (String) items[which];
                            }

                        }).create();

            case 1:
                progressDialog = new ProgressDialog(this);
                progressDialog.setIcon(R.mipmap.ic_launcher);
                progressDialog.setTitle("Preuzimanje datoteka...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(getBaseContext(), "OK je kliknut!", Toast.LENGTH_SHORT).show();

                    }
                });
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(getBaseContext(), "Cancel je kliknut!", Toast.LENGTH_SHORT).show();
                    }
                });
                return progressDialog;
        }
        return null;
    }
}