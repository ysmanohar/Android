package com.example.mannu.afinal;

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
public class Giftadapter extends ArrayAdapter<Person> {
    ArrayList<Person> personslist;
    Context mcontext;
    int mResource;
    TextView name;
    TextView noofgifts;
    TextView moneyspent;
    TextView budget;
    ImageView pic;


    public Giftadapter(Context mcontext, int mResource, ArrayList<Person> personslist) {
        super(mcontext, mResource, personslist);
        this.personslist = personslist;
        this.mcontext = mcontext;
        this.mResource = mResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(mResource, parent, false);
        }

        Log.d("TEST",personslist.get(position).getName());

        name= (TextView) convertView.findViewById(R.id.name);
        name.setText(personslist.get(position).getName());
        noofgifts= (TextView) convertView.findViewById(R.id.noofgifts);
        noofgifts.setText(personslist.get(position).getGiftsbought());
        moneyspent= (TextView) convertView.findViewById(R.id.moneyspent);
        moneyspent.setText(personslist.get(position).getAmountspent());
        budget= (TextView) convertView.findViewById(R.id.budgetamount);
        budget.setText(personslist.get(position).getBudget());
        pic= (ImageView) convertView.findViewById(R.id.homepic);
        pic.setImageResource(R.drawable.person);

        if(personslist.get(position).getAmountspent()==personslist.get(position).getBudget()){

        }

        return convertView;
    }
}
