package com.example.mannu.midterm;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by mannu on 6/9/2016.
 */
public class GetData extends AsyncTask<String, Void, Weather> {

    Weather weather;

    Activity activity;

    public GetData() {
        this.activity = activity;
    }

    @Override
    protected Weather doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection cn = (HttpURLConnection) url.openConnection();
            cn.setRequestMethod("GET");
            cn.connect();
            int status = cn.getResponseCode();
            if(status== HttpsURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(cn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line !=null){
                    sb.append(line);
                    line = reader.readLine();
                }
                try {
                    return Weathercast.currentweather(sb.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Weather s) {
        super.onPostExecute(s);
        Log.d("hihello","hi");

        Log.d("HI", String.valueOf(s)+"hi");

        Intent newintent = new Intent(activity,WeatherSummaryActivity.class);
        newintent.putExtra("KEY", (Serializable) s);
    }
    }

