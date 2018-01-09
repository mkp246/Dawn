package com.manoj.clicker.dawn;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by mkumar14 on 1/9/2018-4:41 PM.
 */

public class SQLiteExample extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlliteexample);
        Button sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        Button sqlView = (Button) findViewById(R.id.bSQLOpenView);
        EditText sqlName = (EditText) findViewById(R.id.etSQLName);
        EditText sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
