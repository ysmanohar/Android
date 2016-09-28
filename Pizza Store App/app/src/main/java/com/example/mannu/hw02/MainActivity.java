package com.example.mannu.hw02;
/** Homework-2
 * Sai Manohar Yerra
 * TJ Sneed
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public static TableRow topRow;
    public static TableRow bottomRow;
    public static ProgressBar toppingsBar;
    public static CheckBox delivery;
    public static CheckBox load;
    public  static String listString ="";

    public static final String PrevToppings = "Prev";
    SharedPreferences sharedpreferences;
    Set<String> toppingset;
    public static final String MyToppings = "MyTopping" ;
    final CharSequence[] items = {"Bacon","Cheese","Garlic","Green Pepper","Mushroom","Olives","Onions","Red Pepper"};
    public static int num;


    public static ArrayList<String> toppings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.app_icon);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);





        topRow = (TableRow)findViewById(R.id.topRow);
        bottomRow = (TableRow)findViewById(R.id.bottomRow);
        toppingsBar = (ProgressBar)findViewById(R.id.progressBar2);
        delivery = (CheckBox)findViewById(R.id.delivery);
        load = (CheckBox) findViewById(R.id.Load);

        sharedpreferences = this.getSharedPreferences(MyToppings, Context.MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Topping").
                setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        load_data(item);
                    }
                });

        final AlertDialog singleItemAlert = builder.create();
        ((Button) findViewById(R.id.addButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(toppingsBar.getProgress()<10){
                    singleItemAlert.show();
                }else{
                    Toast.makeText(MainActivity.this, "Maximum capacity!", Toast.LENGTH_LONG).show();
                }
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(load.isChecked()){
                    String text = sharedpreferences.getString(PrevToppings,null);
                    topRow.removeAllViews();
                    bottomRow.removeAllViews();
                    toppingsBar.setProgress(0);
                    MainActivity.toppings.clear();
                    String delimiter = ",";
                    if(text.equals(null)){
                        Toast.makeText(MainActivity.this,"There is no previous Order to Load!",Toast.LENGTH_LONG).show();
                    }else{
                        String[] temp = text.split(delimiter);
                        for(int i =0; i < temp.length ; i++){
                            switch (temp[i]){
                                case "Bacon" : num=0;
                                    break;
                                case "Cheese": num=1;
                                    break;
                                case "Garlic": num=2;
                                    break;
                                case "Green Pepper": num=3;
                                    break;
                                case "Mushroom": num=4;
                                    break;
                                case "Olives": num=5;
                                    break;
                                case "Onion": num=6;
                                    break;
                                case "Red Pepper": num=7;
                                    break;
                                case " ": num=8;
                                    break;
                            }
                            Log.d("new-array",String.valueOf(num));
                            load_data(num);
                        }
                    }
                }
                else{
                    topRow.removeAllViews();
                    bottomRow.removeAllViews();
                    toppingsBar.setProgress(0);
                    MainActivity.toppings.clear();
                }
            }
        });


        ((Button) findViewById(R.id.clearButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                topRow.removeAllViews();
                bottomRow.removeAllViews();
                toppingsBar.setProgress(0);
                MainActivity.toppings.clear();
                Log.d("new", String.valueOf(toppings));
            }
        });

        ((Button) findViewById(R.id.checkout)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                int checked = 0;
                if(delivery.isChecked()){
                    checked = 1;
                }
                intent.putExtra("delivery",checked);
                intent.putExtra("toppings",MainActivity.toppings);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                for (String s : toppings)
                {
                    listString += s + ",";
                }
                if(toppings.size()==0){listString =" ";}
                Log.d("new1",listString);
                editor.putString(PrevToppings,listString);
                editor.commit();
                listString ="";
                startActivity(intent);
            }
        });
    }


    public final void load_data(int item) {
        ImageButton newTopping = new ImageButton(MainActivity.this);
        TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        newTopping.setLayoutParams(buttonParams);
        switch(item) {
            case 0: newTopping.setBackgroundResource(R.drawable.bacon);
                break;
            case 1: newTopping.setBackgroundResource(R.drawable.cheese);
                break;
            case 2: newTopping.setBackgroundResource(R.drawable.garlic);
                break;
            case 3: newTopping.setBackgroundResource(R.drawable.green_pepper);
                break;
            case 4: newTopping.setBackgroundResource(R.drawable.mushroom);
                break;
            case 5: newTopping.setBackgroundResource(R.drawable.olives);
                break;
            case 6: newTopping.setBackgroundResource(R.drawable.onion);
                break;
            case 7: newTopping.setBackgroundResource(R.drawable.red_pepper);
                break;
            default:
                topRow.removeAllViews();
                bottomRow.removeAllViews();
                toppingsBar.setProgress(0);
                break;
        }
        if(item>=0 && item<=7){
            MainActivity.toppings.add(items[item].toString());
            newTopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = ((TableRow)v.getParent()).indexOfChild(v);
                    ((TableRow)v.getParent()).removeView(v);
                    MainActivity.toppings.remove(index);
                    toppingsBar.setProgress(toppingsBar.getProgress()-1);
                    if(topRow.getChildCount()<5 && bottomRow.getChildCount()>0){
                        ImageButton toppingToMove = (ImageButton)bottomRow.getChildAt(0);
                        bottomRow.removeViewAt(0);
                        topRow.addView(toppingToMove);
                    }
                }
            });
            if(topRow.getChildCount()<5){
                topRow.addView(newTopping);
            }else{
                bottomRow.addView(newTopping);
            }
            toppingsBar.setProgress(toppingsBar.getProgress()+1);
        }
    }
}
