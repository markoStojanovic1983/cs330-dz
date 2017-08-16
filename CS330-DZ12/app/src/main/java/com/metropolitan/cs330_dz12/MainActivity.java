package com.metropolitan.cs330_dz12;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    NotificationManager notificationManager;
    int notifID = 33;


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


        NotificationCompat.Builder notificBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Informacije o izabranoj lokaciji")
                .setContentText("Geografksa sirina: " + lat + "Geografska duzina: " + lon)
                .setTicker("Nova notifikacija")
                .setSmallIcon(R.drawable.ntt_logo_24_24)
                .setAutoCancel(true);


        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(this);

        tStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        notificBuilder.setContentIntent(pendingIntent);

        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifID, notificBuilder.build());


    }
}

