package com.manoj.clicker.dawn;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.Locale;
import java.util.Random;

/**
 * Created by mkumar14 on 1/11/2018-7:26 PM.
 */

public class TextVoice extends Activity implements View.OnClickListener {
    static final String[] texts = {
            "what's up gangstas!",
            "i am don",
            "you are pretty"
    };

    private TextToSpeech tts;

    @Override
    protected void onPause() {
        if (tts != null) tts.stop();
        tts.shutdown();
        super.onPause();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textvoice);
        findViewById(R.id.bTextToVoice).setOnClickListener(this);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        String random = texts[new Random().nextInt(3)];
        tts.speak(random, TextToSpeech.QUEUE_FLUSH, null);
    }
}
