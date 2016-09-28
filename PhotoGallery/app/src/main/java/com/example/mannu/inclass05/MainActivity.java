package com.example.mannu.inclass05;

/** Assignment-5
Sai MAnohar Yerra
TJ Sneed **/

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> dictionary = new ArrayList<String>();
    TextView text;
    ProgressDialog imgProcDialog;
    String value="";
    int track = 1;
    String web ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.feild_label);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Choose a keyword").setItems(R.array.array_items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("hi", String.valueOf(which));
                        switch (which) {
                            case 0:
                                text.setText("UNCC");
                                web= "http://dev.theappsdr.com/apis/summer_2016/inclass5/index.php?keyword=UNCC";
                                break;
                            case 1:
                                text.setText("Android");
                                web ="http://dev.theappsdr.com/apis/summer_2016/inclass5/index.php?keyword=android";
                                break;
                            case 2:
                                text.setText("Winter");
                                web="http://dev.theappsdr.com/apis/summer_2016/inclass5/index.php?keyword=winter";
                                break;
                            case 3:
                                text.setText("Aurora");
                                web="http://dev.theappsdr.com/apis/summer_2016/inclass5/index.php?keyword=aurora";
                                break;
                            case 4:
                                text.setText("Wonders");
                                web="http://dev.theappsdr.com/apis/summer_2016/inclass5/index.php?keyword=wonders";

                                break;
                        }
                        imgProcDialog = new ProgressDialog(MainActivity.this);
                        imgProcDialog.setMessage("Loading Photo");
                        imgProcDialog.setCancelable(false);
                        imgProcDialog.show();
                    }
                });
                builder.create().show();
            }
        });

        if (isOnline()) {
            Log.d("newtesting",web);
           new GetFile().execute(web);

            Log.d("hi","hello");
        } else {
            Log.d("false","false");
            Toast.makeText(this, "Not connected to Internet", Toast.LENGTH_LONG);
        }
    }

        public boolean isOnline(){
            Log.d("this","this");
            ConnectivityManager conMan = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conMan.getActiveNetworkInfo();
            if(networkInfo!= null && networkInfo.isConnected()){
                return true;
                   }
            return false;
                    }



           private class GetFile extends AsyncTask<String, Integer, ArrayList<String>> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                            }

            @Override
            protected void onPostExecute(ArrayList<String> list) {
//                new GetImage().execute(dictionary.get(track));
                super.onPostExecute(list);
            }

            @Override
            protected ArrayList<String> doInBackground(String... params) {
                Log.d("web",params[0]);
                BufferedReader br = null;
                try {
                    if(isOnline()){
                        URL url = new URL(params[0]);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String readLine;
                        StringBuilder sb = new StringBuilder();
                        while((readLine = br.readLine())!= null){
                            sb.append(readLine);
                        }
                        String[] imageurls = sb.toString().split(";");
                        String key= imageurls[0];
                        for(int urlCounter=1;urlCounter<imageurls.length;urlCounter++){
                            Log.d("data",imageurls[urlCounter]);
                               dictionary.add(imageurls[urlCounter]);
                        }
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(br != null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return dictionary;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }
        }

       private class GetImage extends AsyncTask<String,Integer,Bitmap>{

        Bitmap image = null;
        @Override
        protected void onPreExecute() {
            Log.d("test","Phase-1");
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgProcDialog.dismiss();
            ((ImageView)findViewById(R.id.imageView)).setImageBitmap(image);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            URL url = null;
            InputStream ips = null;
            try {
                url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                ips = connection.getInputStream();
                image = BitmapFactory.decodeStream(ips);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(ips!=null) {
                        ips.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return image;
        }
    }
}


