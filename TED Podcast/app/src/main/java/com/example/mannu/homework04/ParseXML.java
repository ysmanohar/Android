package com.example.mannu.homework04;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mannu on 6/20/2016.
 */
public class ParseXML extends AsyncTask<String,Void,ArrayList<AppData>> {
    IData activity;
    ProgressDialog progressDialog;

    public ParseXML(IData activity){
        this.activity = activity;
    }


    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog((Context) activity);
        progressDialog.setTitle("Loading Results....");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(ArrayList<AppData> appDetailses) {
        super.onPostExecute(appDetailses);
        progressDialog.dismiss();
        for (int i =0;i< appDetailses.size();i++){
            Log.d("demo",appDetailses.get(i).toString());
        }
        activity.getData(appDetailses);
    }

    @Override
    protected ArrayList<AppData> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status = con.getResponseCode();
            if(status == HttpURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                Log.d("demo","sent for parsing");
                return AppDetailsUtill.ParseAppDetails.ParseXml(in);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface IData{
        void getData(ArrayList<AppData> w);
    }

}
