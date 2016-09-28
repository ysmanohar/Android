package com.example.mannu.inclass07;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mannu on 6/16/2016.
 */
public class Appadapter extends ArrayAdapter<App> {
    ArrayList<App> app;
    Context mcontext;
    int mResource;
    ImageView image;
ImageView star;

    public Appadapter(Context mcontext, int mResource, ArrayList<App> app ) {
        super(mcontext, mResource, app);
        this.app = app;
        this.mcontext = mcontext;
        this.mResource = mResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            LayoutInflater inflator = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflator.inflate(mResource,parent,false);
        }


        Log.d("screenTitle", String.valueOf(app.get(position).getAppTitle()));
        App curent =app.get(position);



        star= (ImageView) convertView.findViewById(R.id.star);

        star.setImageResource(curent.bookmark);



        TextView Title= (TextView) convertView.findViewById(R.id.textViewTitle);

        Title.setText(String.valueOf(app.get(position).getAppTitle()));

        TextView Dev= (TextView) convertView.findViewById(R.id.textViewDeveloperName);
        Dev.setText(app.get(position).getDevName());

        TextView Date= (TextView) convertView.findViewById(R.id.textViewReleaseDate);
        Date.setText(app.get(position).getDate());

        TextView price= (TextView) convertView.findViewById(R.id.textViewPrice);
        price.setText(app.get(position).getPrice());

        TextView category= (TextView) convertView.findViewById(R.id.textViewCategory);
        category.setText(app.get(position).getCategory());

        ImageView image= (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(mcontext).load(app.get(position).getSmallImage()).into(image);





                  //  image.setImageResource(R.drawable.grey);




     return  convertView;

    }
}
