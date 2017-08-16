package com.metropolitan.cs330_dz03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button zadatak1;
    private Button zadatak2;
    private Button zadatak3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zadatak1 = (Button) findViewById(R.id.zadatak1btn);
        zadatak2 = (Button) findViewById(R.id.zadatak2btn);
        zadatak3 = (Button) findViewById(R.id.zadatak3btn);


        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();

        if (d.getWidth() > d.getHeight()) {
            //pirkazivanje informacija za prelazak u landscape mode
            Toast.makeText(getBaseContext(), "Landscape mode", Toast.LENGTH_SHORT).show();
        } else {
            //pirkazivanje informacija za prelazak u portrait mode
            Toast.makeText(getBaseContext(), "Portrait mode", Toast.LENGTH_SHORT).show();
        }
    }

    public void zadatak1(View V) {
        startActivity(new Intent(MainActivity.this, FirstActivity.class));
    }

    public void zadatak2(View V) {
        startActivity(new Intent(MainActivity.this, MetropolitanUniverzitet.class));
    }

    public void zadatak3(View V) {
        startActivity(new Intent(MainActivity.this, ThirdActivity.class));
    }
}
