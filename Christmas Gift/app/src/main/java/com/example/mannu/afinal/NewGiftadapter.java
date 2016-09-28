package com.example.mannu.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mannu on 6/28/2016.
 */
public class NewGiftadapter extends ArrayAdapter<Gift> {
    ArrayList<Gift> Giftlist;
    Context mcontext;
    int mResource;
    ImageView pic;
    TextView itemname;
    TextView price;


    public NewGiftadapter(Context mcontext, int mResource, ArrayList<Gift> Giftlist) {
        super(mcontext, mResource, Giftlist);
        this.Giftlist = Giftlist;
        this.mcontext = mcontext;
        this.mResource = mResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(mResource, parent, false);
        }

        itemname= (TextView) convertView.findViewById(R.id.name);
        itemname.setText(Giftlist.get(position).getGift());

        price= (TextView) convertView.findViewById(R.id.money);
        price.setText(Giftlist.get(position).getPrice());

        pic= (ImageView) convertView.findViewById(R.id.pic);
        Picasso.with(mcontext).load(Giftlist.get(position).getUrl()).into(pic);


        return convertView;
    }
}
