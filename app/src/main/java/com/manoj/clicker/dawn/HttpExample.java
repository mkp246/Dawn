package com.manoj.clicker.dawn;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import static java.lang.System.in;

/**
 * Created by mkumar14 on 1/10/2018-6:17 PM.
 */

public class HttpExample extends Activity {
    TextView httpStuff;

    public static final String URL = "http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpex);
        httpStuff = (TextView) findViewById(R.id.tvHttp);
//        new NetworkTask().execute("http://www.youtube.com");

        new TwitterTask().execute(URL, "firstpost");
    }

    class NetworkTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPostExecute(String s) {
            httpStuff.setText(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                DefaultHttpClient client = new DefaultHttpClient();
                URI uri = new URI(strings[0]);
                HttpGet httpGet = new HttpGet();
                httpGet.setURI(uri);
                HttpResponse response = client.execute(httpGet);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer();
                String line = "";
                String nl = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append(nl);
                }
                in.close();
                String data = sb.toString();
                return data;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    class TwitterTask extends AsyncTask<String, Integer, JSONObject> {
        @Override
        protected void onPostExecute(JSONObject s) {
            String text = s.toString();
            httpStuff.setText(text);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            try {
                DefaultHttpClient client = new DefaultHttpClient();
                URI uri = new URI(strings[0] + strings[1]);
                HttpGet httpGet = new HttpGet();
                httpGet.setURI(uri);
                HttpResponse response = client.execute(httpGet);
                int status = response.getStatusLine().getStatusCode();
                HttpEntity e = response.getEntity();
                String data = EntityUtils.toString(e);
                JSONObject timeline = new JSONObject(data);
                return timeline;
            } catch (Exception e) {
                Toast.makeText(HttpExample.this, "error", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            return null;
        }
    }
}
