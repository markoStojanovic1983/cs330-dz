package com.metropolitan.cs330_dz03;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by mare on 6/5/17.
 */

public class ThirdActivity extends AppCompatActivity {
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void onClickSubmit(View v) {
        // ---prikazuje dijalog---
        dialog = ProgressDialog.show(this, "Connecting", "Please wait...", true);
        new Thread(new Runnable() {
            public void run() {
                try {
                    // ---simulacija da nešto radi---
                    Thread.sleep(1000);
                    // ---odjavljuje dijalog---
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

