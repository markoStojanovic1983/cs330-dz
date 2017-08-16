package com.metropolitan.cs330_dz11;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayClick(View view) {
        startService(new Intent(getBaseContext(), MusicService.class));
    }

    public void onStopClick(View view) {
        stopService(new Intent(getBaseContext(), MusicService.class));
    }
}

