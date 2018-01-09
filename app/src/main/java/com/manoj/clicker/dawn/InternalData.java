package com.manoj.clicker.dawn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mkumar14 on 1/9/2018-9:00 AM.
 */

public class InternalData extends Activity implements View.OnClickListener {
    EditText sharedData;
    TextView dataResult;
    public final String filename = "InternalString";
    FileOutputStream fos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedprefs);
        Button save = (Button) findViewById(R.id.save);
        Button load = (Button) findViewById(R.id.load);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResult = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        try {
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                String data = sharedData.getText().toString();
                try {
                    fos = openFileOutput(filename, 0);
//                  write to file
                    fos.write(data.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.load:
                new LoadSumStuff().execute(filename);
                break;
        }
    }

    private class LoadSumStuff extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;

        @Override
        protected void onPostExecute(String result) {
            dataResult.setText(result);
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.incrementProgressBy(values[0]);
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(InternalData.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String collected = null;
            FileInputStream fis = null;
            for (int i = 0; i < 20; i++) {
                publishProgress(5);
                try {
                    Thread.sleep(77);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                fis = openFileInput(filename);
                byte[] dataArray = new byte[fis.available()];
                while (fis.read(dataArray) != -1) {
                    //read till end of file
                    collected = new String(dataArray);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    }
}
