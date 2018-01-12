package com.manoj.clicker.dawn;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SeekBar;

/**
 * Created by mkumar14 on 1/11/2018-10:15 PM.
 */

public class SeekBarVolume extends Activity implements SeekBar.OnSeekBarChangeListener {
    SeekBar sb;
    MediaPlayer mp;
    AudioManager am;

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbarvolume);
        sb = (SeekBar) findViewById(R.id.sbVolume);
        mp = MediaPlayer.create(this, R.raw.music);
        mp.start();
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxV = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curV = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        sb.setOnSeekBarChangeListener(this);
        sb.setMax(maxV);
        sb.setProgress(curV);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
