package com.metropolitan.cs330_dz03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mare on 6/5/17.
 */

public class MetropolitanUniverzitet extends AppCompatActivity {
    private TextView faks;
    private TextView faksPotvrda;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metropolitan_univerzitet);
        faks = (TextView) findViewById(R.id.faks);
        faksPotvrda = (TextView) findViewById(R.id.potvrdaFaks);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.metropolitan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        faks.setText(item.getTitle());
        return super.onOptionsItemSelected(item);
    }

    public void onClickDa(View v) {
        String tmp = (String) faks.getText();
        if (!tmp.equals("Empty")) {
            faksPotvrda.setText("Student " + tmp + "-a.");
        }
    }

    public void onClickNe(View v) {
        String tmp = (String) faks.getText();
        if (!tmp.equals("Empty")) {
            faksPotvrda.setText("Niste student " + tmp + "-a.");
        }
    }
}