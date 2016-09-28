package com.example.mannu.life;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mannu on 6/28/2016.
 */
public class Appadapter extends ArrayAdapter<Messages> {
    ArrayList<Messages> messagelist;
    Context mcontext;
    int mResource;
    TextView title;
    TextView date;

    public Appadapter(Context mcontext, int mResource, ArrayList<Messages> messagelist) {
        super(mcontext, mResource, messagelist);
        this.messagelist = messagelist;
        this.mcontext = mcontext;
        this.mResource = mResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflator = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflator.inflate(mResource,parent,false);
        }

        Log.d("COLOR",messagelist.get(position).getIsread());
        if(messagelist.get(position).getIsread().equals("read")){
            ImageView status = (ImageView) convertView.findViewById(R.id.imageView);
            status.setImageResource(R.drawable.dot);
        }
        else{
            ImageView status = (ImageView) convertView.findViewById(R.id.imageView);
            status.setImageResource(R.drawable.red_dot_small);
        }

        title= (TextView) convertView.findViewById(R.id.name);
        title.setText(messagelist.get(position).getSender());

        date= (TextView) convertView.findViewById(R.id.date);
        date.setText(messagelist.get(position).getTime());




        return  convertView;
    }
}
