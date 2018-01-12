package com.manoj.clicker.dawn;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;

/**
 * Created by mkumar14 on 1/11/2018-7:39 PM.
 */

public class StatusBar extends Activity implements View.OnClickListener {
    NotificationManager nm;
    public static final int uniqueId = 1111;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusbar);
        findViewById(R.id.bStatusBar).setOnClickListener(this);
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, StatusBar.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        String body = "This is a message";
        String title = "Travis C.";
        Notification.Builder nb = new Notification.Builder(this);
        nb.setSmallIcon(R.drawable.icon);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.greenbot);
        nb.setLargeIcon(bitmap);
        nb.setContentTitle(title);
        nb.setContentText(body);
        nb.setContentIntent(pi);
        nb.setDefaults(Notification.DEFAULT_ALL);
        nb.setAutoCancel(true);
        nb.setVibrate(new long[]{Notification.DEFAULT_VIBRATE});
        Notification n = nb.build();
        nm.notify(uniqueId, n);
        finish();
    }
}
