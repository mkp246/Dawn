package com.manoj.clicker.dawn;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.storage.StorageManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mkumar14 on 1/9/2018-12:34 PM.
 */

public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private TextView canWrite, canRead;
    private String state;
    boolean canR, canW;
    Spinner spinner;
    String[] paths = {"Music", "Pictures", "Download"};
    File path = null;
    EditText saveFile;
    Button confirm, save;
    File file = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        canRead = (TextView) findViewById(R.id.tvCanRead);
        canRead.setText("false");
        canWrite.setText("false");

        confirm = (Button) findViewById(R.id.bConfirmSaveAs);
        save = (Button) findViewById(R.id.bSaveFile);
        saveFile = (EditText) findViewById(R.id.etSaveAs);

        confirm.setOnClickListener(this);
        save.setOnClickListener(this);
        checkState();
        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paths);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void checkState() {
        state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            //RW
            canRead.setText("true");
            canWrite.setText("true");
            canR = canW = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            //RO
            canRead.setText("true");
            canWrite.setText("false");
            canR = true;
            canW = false;
        } else if (state.equals(Environment.MEDIA_UNMOUNTED)) {
            //no media
            canR = canW = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int pos = spinner.getSelectedItemPosition();
        switch (pos) {
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            // This is where you do your work in the UI thread.
            // Your worker tells you in the message what to do.
            Toast t = Toast.makeText(ExternalData.this, "Scan complete", Toast.LENGTH_LONG);
            t.show();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bConfirmSaveAs:
                save.setVisibility(View.VISIBLE);
                break;
            case R.id.bSaveFile:
                String f = saveFile.getText().toString();
                file = new File(path, f + ".mp3");
                checkState();
                if (canR = canW = true) {
                    path.mkdirs();
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        InputStream is = getResources().openRawResource(R.raw.explosion);
                        OutputStream os = new FileOutputStream(file);
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();
                        Toast t = Toast.makeText(this, "File saved", Toast.LENGTH_LONG);
                        t.show();

                        MediaScannerConnection.scanFile(ExternalData.this, new String[]{file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Message message = mHandler.obtainMessage();
                                message.sendToTarget();
                            }
                        });
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
