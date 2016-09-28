package com.example.mannu.hw03;

/** Homework-6
 * Sai Manohar Yerra
 * TJ Sneed
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetAppDataAsyncTask.AppData {
    static int index=0;
    TextView title;
    TextView devname;
    TextView date;
    TextView price;
    ImageView image;
    ImageButton left;
    ImageButton right;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title= (TextView) findViewById(R.id.Title);
        devname= (TextView) findViewById(R.id.Devname);
        date= (TextView) findViewById(R.id.Date);
        price= (TextView) findViewById(R.id.Price);
        image= (ImageView) findViewById(R.id.image);
        left= (ImageButton) findViewById(R.id.left);
        right= (ImageButton) findViewById(R.id.right);

        new GetAppDataAsyncTask(this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml");

                   }

    @Override
    public void setupData(final ArrayList<App> applist) {
        title.setText(applist.get(index).getAppTitle());
        devname.setText(applist.get(index).getDevName());
        date.setText(applist.get(index).getDate());
        price.setText(applist.get(index).getPrice());
        Picasso.with(this).load(applist.get(index).getLargeImage()).into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(applist.get(index).getUrl())));
                startActivity(intent);
            }
        });


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index=index+1;
                if(index==100){
                    index=0;
                }
                title.setText(applist.get(index).getAppTitle());
                devname.setText(applist.get(index).getDevName());
                date.setText(applist.get(index).getDate());
                price.setText(applist.get(index).getPrice());
                Picasso.with(MainActivity.this).load(applist.get(index).getLargeImage()).into(image);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index=index-1;
                if(index==-1){
                    index=99;
                }
                title.setText(applist.get(index).getAppTitle());
                devname.setText(applist.get(index).getDevName());
                date.setText(applist.get(index).getDate());
                price.setText(applist.get(index).getPrice());
                Picasso.with(MainActivity.this).load(applist.get(index).getLargeImage()).into(image);

            }
        });



    }

    @Override
    public Context getContext() {
        return null;
    }
}
