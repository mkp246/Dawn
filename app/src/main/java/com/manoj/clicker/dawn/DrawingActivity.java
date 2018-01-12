package com.manoj.clicker.dawn;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Browser;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by mkumar14 on 12/16/2017.
 */

public class DrawingActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        PackageManager packageManager = getPackageManager();
        if (menuItem.getItemId() == R.id.item1) {
            Intent poweramp = packageManager.getLaunchIntentForPackage("com.maxmpz.audioplayer");
            startActivity(poweramp);
        } else if (menuItem.getItemId() == R.id.item2) {
            Intent camera = packageManager.getLaunchIntentForPackage("com.motorola.cameraone");
            startActivity(camera);
        } else if (menuItem.getItemId() == R.id.radio) {
            Intent intent = new Intent(this, Data.class);
            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.aboutUs) {
            Intent i = new Intent("com.dawn.ABOUT");
            startActivity(i);
        } else if (menuItem.getItemId() == R.id.preferences) {
            Intent p = new Intent("com.dawn.PREFS");
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.gfx) {
            Intent p = new Intent(this, GFX.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.gfxSurface) {
            Intent p = new Intent(this, GFXSurface.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.soundStuff) {
            Intent p = new Intent(this, SoundStuff.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.slider) {
            Intent p = new Intent(this, Slider.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.tabs) {
            Intent p = new Intent(this, Tabs.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.browser) {
            Intent p = new Intent(this, SimpleBrowser.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.flipper) {
            Intent p = new Intent(this, Flipper.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.sharedPrefs) {
            Intent p = new Intent(this, SharedPrefs.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.internalData) {
            Intent p = new Intent(this, InternalData.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.externalData) {
            Intent p = new Intent(this, ExternalData.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.sqliteexample) {
            Intent p = new Intent(this, SQLiteExample.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.accelerate) {
            Intent p = new Intent(this, Accelerate.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.httpexample) {
            Intent p = new Intent(this, HttpExample.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.glexample) {
            Intent p = new Intent(this, GLExample.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.glcubeexample) {
            Intent p = new Intent(this, GLCubeEx.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.voice) {
            Intent p = new Intent(this, Voice.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.textvoice) {
            Intent p = new Intent(this, TextVoice.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.statusbar) {
            Intent p = new Intent(this, StatusBar.class);
            startActivity(p);
        } else if (menuItem.getItemId() == R.id.seekbarvolume) {
            Intent p = new Intent(this, SeekBarVolume.class);
            startActivity(p);
        }
        return true;
    }
}
