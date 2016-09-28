package com.example.mannu.inclass07;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by mannu on 6/16/2016.
 */
public class SecondScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()| ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imgView = new ImageView(this);
        imgView.setScaleType(ImageView.ScaleType.FIT_START);
        actionBar.setCustomView(imgView);
        actionBar.setTitle("Apps Activity");
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        if(getIntent().getExtras()!=null){
            String title=getIntent().getExtras().getString("TITLE");
            String icon=getIntent().getExtras().getString("ICON");


            ImageView image= (ImageView)findViewById(R.id.imageView2);
            Picasso.with(this).load(icon).into(image);

            TextView text= (TextView) findViewById(R.id.texttitle);
            text.setText(title);


        }
    }
}
