package com.test.myapp.thenewboston;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by hp on 07-03-2016.
 * adding background and sound
 */
public class Splash extends Activity {
    MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle TravisLoveBacon) {
        super.onCreate(TravisLoveBacon);
        setContentView(R.layout.splash);
        ourSong = MediaPlayer.create(Splash.this,R.raw.kalimba);
        SharedPreferences getPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPreference.getBoolean("chekcbox",true);
                if(music == true){
        ourSong.start();}

        Thread timer = new Thread()
        {
            public void run()
            {
                try{
                    sleep(1000);
            } catch(InterruptedException e)
                {
                    e.printStackTrace();

                } finally {
                    Intent OpenMainActivity = new Intent("com.thenewboston.MENU");
                    startActivity(OpenMainActivity);

                }
                }

        };
        timer.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}
