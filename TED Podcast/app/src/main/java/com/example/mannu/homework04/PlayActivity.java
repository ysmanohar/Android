package com.example.mannu.homework04;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PlayActivity extends AppCompatActivity {


    private ImageView buttonPlayPause;
    private MediaPlayer mediaPlayer;
    private int mediaFileLengthInMilliseconds;
    private ProgressBar progressBar;
    private final Handler handler = new Handler();

    @Override
    public void onBackPressed() {

        if(mediaPlayer!=null) {
            mediaPlayer.stop();
        }
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ted_icon);

        final AppData appData = (AppData) getIntent().getExtras().getSerializable("Data");

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        Picasso.with(this).load(appData.getImage_URL()).into(imageView);
        TextView textView = (TextView) findViewById(R.id.textViewTitle1);
        textView.setText(appData.getTitle());
        textView = (TextView) findViewById(R.id.textViewDesValue);
        textView.setText(appData.getDescription());
        textView = (TextView) findViewById(R.id.textViewDateValue);
        textView.setText(appData.getDate());
        // Log.d("duration",appData.getDuration());
        textView = (TextView) findViewById(R.id.textViewDurationValue);
        textView.setText(appData.getDuration()+" secs");
        progressBar = (ProgressBar) findViewById(R.id.ProgressBar2);
        progressBar.setMax(99);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                progressBar.setProgress(0);
                buttonPlayPause.setImageResource(Resources.getSystem().getIdentifier("ic_media_play", "drawable", "android"));


            }
        });

        buttonPlayPause = (ImageView) findViewById(R.id.Pause2);
        buttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });
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
            handler.postDelayed(notification, 1000);
        }


    }

}
