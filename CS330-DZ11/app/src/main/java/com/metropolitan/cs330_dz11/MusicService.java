package com.metropolitan.cs330_dz11;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by mare on 6/14/17.
 */


public class MusicService extends Service implements MediaPlayer.OnPreparedListener {


    MediaPlayer player;

    public IBinder onBind(Intent arg0) {

        return null;
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            player = MediaPlayer.create(this, R.raw.sample);
            player.setLooping(false);
            player.setVolume(100, 100);
            player.setOnPreparedListener(this);
            player.prepareAsync(); // Integrisan Async metod unutar playera, koji onemogucava blokiranje glavne niti

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onPrepared(MediaPlayer player) {
        player.start();
    }

    public void onStart(Intent intent, int startId) {

    }


    @Override
    public void onDestroy() {

        player.stop();
        player.release();
    }
}
