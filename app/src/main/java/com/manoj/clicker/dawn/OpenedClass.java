package com.manoj.clicker.dawn;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by mkumar14 on 12/29/2017-8:44 PM.
 */

public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    TextView question, test;
    Button returnData;
    RadioGroup selectionList;
    String gotBread;
    String setData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();
        Bundle gotBasket = getIntent().getExtras();
        gotBread = gotBasket.getString("key");
        question.setText(gotBread);
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name", "Khetapur is...");
        int values = Integer.parseInt(getData.getString("list", "1"));
        if (values == 1) {
            question.setText(et);
        }
    }

    private void initialize() {
        question = (TextView) findViewById(R.id.tvQuestion);
        test = (TextView) findViewById(R.id.tvText);
        returnData = (Button) findViewById(R.id.bReturn);
        returnData.setOnClickListener(this);
        selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
        selectionList.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent person = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("answer", setData);
        person.putExtras(backpack);
        setResult(RESULT_OK, person);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rCrazy:
                setData = "Probably Right!";
                break;
            case R.id.rSexy:
                setData = "Definitely Right!";
                break;
            case R.id.rBoth:
                setData = "Spot On!";
                break;
        }
        test.setText(setData);
    }
}
