package com.manoj.clicker.dawn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by mkumar14 on 1/7/2018-9:08 PM.
 */

class MyBringBack extends View {
    Bitmap gBall;
    float changingY;
    Typeface font;

    public MyBringBack(Context context) {
        super(context);
        changingY = 0;
        gBall = BitmapFactory.decodeResource(getResources(), R.drawable.greenbot);
        font = Typeface.createFromAsset(context.getAssets(), "Gabriola.ttf");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        Paint textPaint = new Paint();
        textPaint.setARGB(50, 255, 11, 55);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(100);
        textPaint.setTypeface(font);
        canvas.drawText("My Bring back", canvas.getWidth() / 2, 200, textPaint);
        canvas.drawBitmap(gBall, canvas.getWidth() / 2, changingY, null);
        if (changingY < canvas.getHeight()) {
            changingY += 10;
        } else {
            changingY = 0;
        }
        Rect middleRect = new Rect();
        middleRect.set(0, 400, canvas.getWidth(), 550);
        Paint ourBlue = new Paint();
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, ourBlue);
        invalidate();
    }
}
