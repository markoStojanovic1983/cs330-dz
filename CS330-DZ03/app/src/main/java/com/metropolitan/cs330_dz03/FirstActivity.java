package com.metropolitan.cs330_dz03;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mare on 6/5/17.
 */

public class FirstActivity extends AppCompatActivity {
    private Button nazivBtn;
    private Button lokacijaBtn;
    private TextView naziv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        nazivBtn = (Button) findViewById(R.id.nazivBtn);
        lokacijaBtn = (Button) findViewById(R.id.lokacijaBtn);
        naziv = (TextView) findViewById(R.id.naziv);
    }


    public void prikaziNaziv(View v) {
        naziv.setText("Univerzitet Metropolitan");
    }

    public void prikaziLokaciju(View v) {

        Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.rs/maps/@44.0255012,20.8522824,15z?hl=sr"));
        startActivity(i);
    }
}

