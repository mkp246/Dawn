package com.manoj.clicker.dawn;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mkumar14 on 1/9/2018-4:41 PM.
 */

public class SQLiteExample extends Activity implements View.OnClickListener {
    EditText sqlName, sqlHotness, sqlRow;

    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlliteexample);
        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlView = (Button) findViewById(R.id.bSQLOpenView);
        sqlName = (EditText) findViewById(R.id.etSQLName);
        sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
        sqlModify = (Button) findViewById(R.id.bSQLModify);
        sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
        sqlDelete = (Button) findViewById(R.id.bSQLDelete);
        sqlRow = (EditText) findViewById(R.id.etSQLRowInfo);

        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);
        sqlModify.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSQLUpdate:
                boolean didItWork = true;
                try {
                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();
                    HotOrNot entry = new HotOrNot(SQLiteExample.this);
                    entry.open();
                    entry.createEntry(name, hotness);
                    entry.close();
                } catch (Exception e) {
                    didItWork = false;
                } finally {
                    if (didItWork) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Heck yeah!!");
                        TextView tv = new TextView(this);
                        tv.setText("success");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
            case R.id.bSQLOpenView:
                Intent i = new Intent(this, SQLView.class);
                startActivity(i);
                break;
            case R.id.bGetInfo:
                String s = sqlRow.getText().toString();
                long l = Long.parseLong(s);
                HotOrNot hon = new HotOrNot(this);
                hon.open();
                String returnedName = hon.getName(l);
                String returnedHotness = hon.getHotness(l);
                sqlName.setText(returnedName);
                sqlHotness.setText(returnedHotness);
                hon.close();
                break;
            case R.id.bSQLModify:
                String mName = sqlName.getText().toString();
                String mHotness = sqlHotness.getText().toString();
                String sRow = sqlRow.getText().toString();
                HotOrNot ex = new HotOrNot(this);
                ex.open();
                ex.updateEntry(Long.parseLong(sRow), mName, mHotness);
                ex.close();
                break;
            case R.id.bSQLDelete:
                String sRow1 = sqlRow.getText().toString();
                HotOrNot ex1 = new HotOrNot(this);
                ex1.open();
                ex1.deleteEntry(Long.parseLong(sRow1));
                ex1.close();
                break;
        }

    }
}
