package com.manoj.clicker.dawn;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by mkumar14 on 1/8/2018-6:40 PM.
 */

public class Tabs extends Activity implements View.OnClickListener {
    TabHost th;
    TextView showResults;
    long start, stop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        Button newTab = (Button) findViewById(R.id.bAddTab);
        Button bStart = (Button) findViewById(R.id.bStart);
        Button bStop = (Button) findViewById(R.id.bStop);
        showResults = (TextView) findViewById(R.id.tvShowResults);

        newTab.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);
        start = 0;
        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();
        TabHost.TabSpec specs = th.newTabSpec("tab1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("StopWatch");
        th.addTab(specs);
        specs = th.newTabSpec("tab2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("tab2");
        th.addTab(specs);
        specs = th.newTabSpec("tab3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a tab");
        th.addTab(specs);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bAddTab:
                TabHost.TabSpec ourSpec = th.newTabSpec("tag1");
                ourSpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("New tab is created now.");
                        return text;
                    }
                });
                ourSpec.setIndicator("tag1");
                th.addTab(ourSpec);
                break;
            case R.id.bStart:
                start = System.currentTimeMillis();
                break;
            case R.id.bStop:
                stop = System.currentTimeMillis();
                if (start != 0) {
                    long result = stop - start;
                    int mills = (int) result;
                    int seconds = (int) result / 1000;
                    int minutes = (int) seconds / 60;
                    mills /= 100;
                    seconds %= 60;
                    showResults.setText(String.format("%d:%02d :%02d", minutes, seconds, mills));
                }
                break;
        }
    }
}
