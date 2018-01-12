package com.manoj.clicker.dawn;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by mkumar14 on 1/10/2018-8:41 AM.
 */

public class Accelerate extends Activity implements SensorEventListener {

    float x, y, sensorX, sensorY;
    Bitmap ball;
    SensorManager sm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.greenbot);
        x = y = sensorY = sensorX = 0;
        MyBringBackSurface myBringBackSurface = new MyBringBackSurface(this);
        myBringBackSurface.resume();
        setContentView(myBringBackSurface);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(18);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    class MyBringBackSurface extends SurfaceView implements Runnable {
        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder();
        }

        @Override
        public void run() {
            while (isRunning) {
                if (!ourHolder.getSurface().isValid()) {
                    continue;
                }
                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(2, 2, 104);
                float centerX = canvas.getWidth() / 2;
                float centerY = canvas.getHeight() / 2;
                canvas.drawBitmap(ball, centerX + sensorX * 20, sensorY * 20 + centerY, null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            sm.unregisterListener(Accelerate.this);
        }

        public void resume() {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }
    }

}
