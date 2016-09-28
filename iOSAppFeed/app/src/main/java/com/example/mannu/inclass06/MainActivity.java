package com.example.mannu.inclass06;

/** Assignment-6
 * Sai Manohar
 * TJ Sneed
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetAppDataAsyncTask.AppData{
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetAppDataAsyncTask(this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml");


    }



    @Override
    public void setupData(final ArrayList<App> applist) {
        Log.d("Title", String.valueOf(applist.get(0).getAppTitle()));
        listview = (ListView) findViewById(R.id.listView);
        Appadapter adapter=new Appadapter(MainActivity.this,R.layout.istitems,applist);
        listview.setAdapter(adapter);
        adapter.setNotifyOnChange(true);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,SecondScreen.class);
                intent.putExtra("TITLE",applist.get(position).appTitle);
                intent.putExtra("ICON",applist.get(position).getLargeImage());
                startActivity(intent);
            }
        });


    }

    @Override
    public Context getContext() {
        return null;
    }
}

