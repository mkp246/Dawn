package com.manoj.clicker.dawn;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by mkumar14 on 1/11/2018-9:39 AM.
 */

public class GLCubeEx extends Activity {
    GLSurfaceView ourSurface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurface = new GLSurfaceView(this);
        ourSurface.setRenderer(new GLCubeRendererEX());
        setContentView(ourSurface);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurface.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurface.onPause();
    }
}