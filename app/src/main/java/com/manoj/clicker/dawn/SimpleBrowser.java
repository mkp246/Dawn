package com.manoj.clicker.dawn;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by mkumar14 on 1/8/2018-9:15 PM.
 */

public class SimpleBrowser extends Activity implements View.OnClickListener {
    EditText url;
    WebView ourBrowser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_browser);
        ourBrowser = (WebView) findViewById(R.id.wvBrowser);
        ourBrowser.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        ourBrowser.getSettings().setJavaScriptEnabled(true);
        try {
            ourBrowser.loadUrl("http://www.google.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
        url = (EditText) findViewById(R.id.etUrl);
        url.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
        Button go = (Button) findViewById(R.id.go);
        Button back = (Button) findViewById(R.id.back);
        Button forward = (Button) findViewById(R.id.forward);
        Button refresh = (Button) findViewById(R.id.refresh);
        Button clearHistory = (Button) findViewById(R.id.clearHistory);
        go.setOnClickListener(this);
        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        refresh.setOnClickListener(this);
        clearHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go:
                String website = url.getText().toString();
                ourBrowser.loadUrl(website);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
                break;
            case R.id.back:
                if (ourBrowser.canGoBack()) ourBrowser.goBack();
                break;
            case R.id.forward:
                if (ourBrowser.canGoBack()) ourBrowser.goForward();
                break;
            case R.id.refresh:
                ourBrowser.reload();
                break;
            case R.id.clearHistory:
                ourBrowser.clearHistory();
                break;
        }
    }
}
