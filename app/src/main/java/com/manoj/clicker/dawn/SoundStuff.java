package com.manoj.clicker.dawn;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by mkumar14 on 1/8/2018-3:27 PM.
 */

public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener {
    SoundPool sp;
    int explosion;
    MediaPlayer mp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = sp.load(this, R.raw.explosion, 0);
        mp = MediaPlayer.create(this,R.raw.backgroundmusic);
    }

    @Override
    public void onClick(View v) {
        if (explosion != 0) sp.play(explosion, 1, 1, 0, 0, 1);
    }

    @Override
    public boolean onLongClick(View v) {
        mp.start();
        return false;
    }
}

