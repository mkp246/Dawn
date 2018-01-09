package com.manoj.clicker.dawn;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mkumar14 on 1/8/2018-10:35 PM.
 */

public class SharedPrefs extends Activity implements View.OnClickListener {
    EditText sharedData;
    TextView dataResult;
    public final String filename = "MYSharedString";
    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedprefs);
        Button save = (Button) findViewById(R.id.save);
        Button load = (Button) findViewById(R.id.load);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResult = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        sp = getSharedPreferences(filename, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                String data = sharedData.getText().toString();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("sharedString", data);
                editor.commit();
                break;
            case R.id.load:
                String load = sp.getString("sharedString", "Couldn't load data");
                dataResult.setText(load);
                break;
        }
    }
}
