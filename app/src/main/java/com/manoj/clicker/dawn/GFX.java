package com.manoj.clicker.dawn;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.os.PowerManager.WakeLock;

/**
 * Created by mkumar14 on 1/7/2018-9:03 PM.
 */

public class GFX extends Activity {
    MyBringBack ourView;
    WakeLock wl;

    @Override
    protected void onPause() {
        super.onPause();
        wl.release();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //wake lock
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");
        wl.acquire();
        super.onCreate(savedInstanceState);
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }
}
