package com.example.mannu.inclass08;

/**
 * Created by mannu on 6/21/2016.
 */

import android.content.Context;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetAppDataAsyncTask extends AsyncTask<String, Void, ArrayList<App>> {

    SecondScreen activity;

    public GetAppDataAsyncTask(SecondScreen activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<App> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            if(statusCode== HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                return AppUtil.AppPullParser.parseApps(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<App> apps) {
        super.onPostExecute(apps);
        activity.setupData(apps);

    }

    static  public interface AppData{
        public  void setupData(ArrayList<App> applist);
        public Context getContext();
    }
}
