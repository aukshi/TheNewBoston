package com.test.myapp.thenewboston;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Created by hp on 21-07-2016.
 * To explore soungpool class
 *
 */
public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener {

    SoundPool sp; //SoundPool is for playing short clips effectively
    MediaPlayer mp; //MediaPlayer is for long clips, especially background music
    int explosion=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener( this);
        v.setOnLongClickListener(this);
        setContentView(v);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC,0); //5-how many max explosions allowed, then stream type, and then sample rate(doesn't have any effect now)
        explosion = sp.load(this,R.raw.kalimba,1);
         mp = MediaPlayer.create(this,R.raw.backgroundmusic);


    }


    @Override
    public void onClick(View v) {
        if(explosion!=0)
            sp.play(explosion,1,1,0,0,1); //soundId,left volume, right volume, priority, loop (-1 for forever looping), flow rate

    }

    @Override
    public boolean onLongClick(View v) {
        mp.start();
        return false;
    }
}
