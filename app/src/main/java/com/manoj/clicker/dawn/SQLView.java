package com.manoj.clicker.dawn;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by mkumar14 on 1/9/2018-4:46 PM.
 */

public class SQLView extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tv = (TextView) findViewById(R.id.tvSQLInfo);
        HotOrNot info = new HotOrNot(this);
        info.open();
        String data = info.getData();
        tv.setText(data);
        info.close();
    }
}
