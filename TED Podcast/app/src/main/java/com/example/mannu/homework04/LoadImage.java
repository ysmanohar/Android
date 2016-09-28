package com.example.mannu.homework04;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by mannu on 6/21/2016.
 */
public class LoadImage extends AsyncTask<String,Void,Bitmap> {

    MainAdapter mainAdapter;
    public LoadImage(MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        mainAdapter.setBitmap(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;

        try {
            url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status = con.getResponseCode();
            if(status==HttpURLConnection.HTTP_OK){
                Bitmap bit = BitmapFactory.decodeStream((InputStream)url.getContent());
                return bit;
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
}
