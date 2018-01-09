package com.manoj.clicker.dawn;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by mkumar14 on 1/8/2018-10:22 PM.
 */

public class Flipper extends Activity implements View.OnClickListener {
    ViewFlipper flippy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
        flippy = (ViewFlipper) findViewById(R.id.flipper1);
        flippy.setOnClickListener(this);
        flippy.setFlipInterval(1000);
        flippy.startFlipping();
    }

    @Override
    public void onClick(View v) {
        flippy.showNext();
    }
}
