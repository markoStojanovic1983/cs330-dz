package com.metropolitan.cs330_dz09;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


/**
 * Created by mare on 6/11/17.
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPrikaz(View view) {
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        float lat = Float.parseFloat(((EditText) findViewById(R.id.latInput)).getText().toString());
        float lon = Float.parseFloat(((EditText) findViewById(R.id.longInput)).getText().toString());
        if (lat < -90 || lat > 90)
            return;
        if (lon < -180 || lon > 180)
            return;
        intent.putExtra("lat", lat);
        intent.putExtra("long", lon);
        startActivity(intent);
    }
}
