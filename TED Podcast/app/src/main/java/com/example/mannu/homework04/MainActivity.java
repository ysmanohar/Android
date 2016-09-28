package com.example.mannu.homework04;

/* HomeWork-04
Sai Manohar Yerra
TJ Sneed
 */

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public  class  MainActivity extends AppCompatActivity implements ParseXML.IData {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView buttonPlayPause;
    private MediaPlayer mediaPlayer;
    private int mediaFileLengthInMilliseconds;
    private ProgressBar progressBar;
    private final Handler handler = new Handler();
    public int layout = 1;
    private ArrayList<AppData> Details;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                break;

            case R.id.action_favorite:
                if (layout == 1) {
                    layout = 2;
                    display(Details);
                } else if (layout == 2) {
                    layout = 1;
                    display(Details);
                }
                break;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
        return true;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ted_icon);


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        View child = getLayoutInflater().inflate(R.layout.linearview, null);
        linearLayout.addView(child);

        if(iSConnectedOnline()) {
            Log.d("demo", "success");
            String url = "http://www.npr.org/rss/podcast.php?id=510298";
            mediaPlayer = new MediaPlayer();
            new ParseXML(this).execute(url);

            progressBar = (ProgressBar) findViewById(R.id.ProgressBar);
            progressBar.setMax(99);
            buttonPlayPause = (ImageView) findViewById(R.id.Pause);


        }
        else{
            Toast.makeText(MainActivity.this, "NO Network connection", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean iSConnectedOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;

    }

    @Override
    public void getData(ArrayList<AppData> result) {
        Details = result;
        display(Details);

    }
    void display(final ArrayList<AppData> result){
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager

        mLayoutManager = new GridLayoutManager(this,layout);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MainAdapter(this,result,layout,mRecyclerView,new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, final AppData item) {
                if(v.getTag().equals("view")){
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        buttonPlayPause.setImageResource(Resources.getSystem().getIdentifier("ic_media_play", "drawable", "android"));
                    }
                    Intent intent = new Intent(MainActivity.this,PlayActivity.class);
                    intent.putExtra("Data",item);
                    startActivity(intent);

                }else if(v.getTag().equals("button")){

                    buttonPlayPause.setImageResource(Resources.getSystem().getIdentifier("ic_media_play", "drawable", "android"));

                    progressBar.setVisibility(View.VISIBLE);
                    buttonPlayPause.setVisibility(View.VISIBLE);
                    if(mediaPlayer!=null) {
                        mediaPlayer.reset();
                    }
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.reset();
                            progressBar.setProgress(0);
                            buttonPlayPause.setImageResource(Resources.getSystem().getIdentifier("ic_media_play", "drawable", "android"));

                            progressBar.setVisibility(View.INVISIBLE);
                            buttonPlayPause.setVisibility(View.INVISIBLE);

                        }
                    });
                    //Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                    buttonPlayPause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            audioplay(item);
                        }
                    });
                    audioplay(item);

                }

            }
        }
        );
        mRecyclerView.setAdapter(mAdapter);

    }

    public  void audioplay(AppData appData){

        try {
            Log.d("demo1", appData.getPodcast().toString());
            mediaPlayer.setDataSource(appData.getPodcast()); // setup song from https://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3 URL to mediaplayer data source
            mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
        } catch (Exception e) {
            e.printStackTrace();

        }

        mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            buttonPlayPause.setImageResource(Resources.getSystem().getIdentifier("ic_media_pause", "drawable", "android"));
        } else {
            mediaPlayer.pause();
            buttonPlayPause.setImageResource(Resources.getSystem().getIdentifier("ic_media_play", "drawable", "android"));
        }

        ProgressUpdater();

    }
    private void ProgressUpdater() {
        progressBar.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaFileLengthInMilliseconds)*100));
        // This math construction give a percentage of "was playing"/"song length"
        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    ProgressUpdater();
                }
            };
            handler.postDelayed(notification,1000);
        }
    }
}
