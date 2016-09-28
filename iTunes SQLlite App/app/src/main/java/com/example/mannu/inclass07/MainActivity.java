package com.example.mannu.inclass07;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetAppDataAsyncTask.AppData {
    ListView listview;
    DatabaseDataManager db;
    ArrayList<App> newList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= new DatabaseDataManager(MainActivity.this);
        newList=new ArrayList<App>();
        new GetAppDataAsyncTask(this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void setupData(final ArrayList<App> applist) {
        newList = applist;
        Log.d("Title", String.valueOf(applist.get(0).getAppTitle()));
        listview = (ListView) findViewById(R.id.listView);
        Appadapter adapter = new Appadapter(MainActivity.this, R.layout.istitems, applist);
        listview.setAdapter(adapter);
        adapter.setNotifyOnChange(true);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondScreen.class);
                intent.putExtra("TITLE", applist.get(position).appTitle);
                intent.putExtra("ICON", applist.get(position).getLargeImage());
                startActivity(intent);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                App app = (App) listview.getItemAtPosition(position);

                if(db.check(applist.get(position).getAppTitle())){
                    db.deleteNote(applist.get(position));
                    app.bookmark = R.drawable.grey;
                    Toast.makeText(MainActivity.this,"Successful Deleted",Toast.LENGTH_SHORT).show();
                }
                else
                { app.bookmark=R.drawable.yellow;
                    db.saveNote(new App(applist.get(position).getId(),applist.get(position).getAppTitle(),applist.get(position).getDevName(),applist.get(position).getPrice(),applist.get(position).getCategory(),applist.get(position).getDate(),true,applist.get(position).getSmallImage()));
                    Toast.makeText(MainActivity.this,"Successful Added",Toast.LENGTH_SHORT).show();
                }
                setupData(newList);
                return true;


             /*   Log.d("Result", String.valueOf(position));
                if(db.check(applist.get(position).getAppTitle())){
                    db.deleteNote(applist.get(position));

                    Log.d("Status","It went");}
                else{
                    db.saveNote(new App(applist.get(position).getId(),applist.get(position).getAppTitle(),applist.get(position).getDevName(),applist.get(position).getPrice(),applist.get(position).getCategory(),applist.get(position).getDate(),true,applist.get(position).getSmallImage()));
                    Log.d("Status","It didnt went");}  */
                    // image.setImageResource(R.drawable.yellow);

            }
        });
        }

    @Override
    public Context getContext() {
        return null;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.show) {
            ArrayList<App>List = (ArrayList<App>) db.getAllNotes();
            for(int i=0;i<List.size();i++){
                List.get(i).bookmark=R.drawable.yellow;
            }
            setupData(List);
            Log.v("show", "@");
            return true;
        }else{
            new GetAppDataAsyncTask(this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml");            Log.v("clear","@");
        }

        return super.onOptionsItemSelected(item);
    }

}


