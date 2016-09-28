package com.example.mannu.hw02;
/** Homework-2
 * Sai Manohar Yerra
 * TJ Sneed
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mannu on 6/7/2016.
 */
public class SecondActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        int checked = 0;
        if(getIntent().getExtras()!= null){
            checked = getIntent().getIntExtra("delivery",0);
        }
        TextView Base = (TextView) findViewById(R.id.Base);
        Base.setText("6.5$");
        int numTop = MainActivity.toppings.size();
        TextView toppingsPrice = (TextView)findViewById(R.id.Topping);
        toppingsPrice.setText(1.5*numTop + "$");
        String lineOne = "";
        String lineTwo = "";
        int i = 0;
        for(String item: MainActivity.toppings){
            if(i < 5){
                lineOne = lineOne + item;
                i++;
                if(i < 5 && i <= numTop){
                    lineOne = lineOne + ", ";
                }
            }else{
                lineTwo = lineTwo + item;
                i++;
                if(i <= numTop){
                    lineTwo = lineTwo + ", ";
                }
            }
        }
        TextView toppingsOne = (TextView)findViewById(R.id.Topping1);
        TextView toppingsTwo = (TextView)findViewById(R.id.Topping2);
        toppingsOne.setText(lineOne);
        toppingsTwo.setText(lineTwo);

        TextView deliveryText = (TextView)findViewById(R.id.Delivery);
        deliveryText.setText(2.0*checked + "$");

        TextView finalText = (TextView)findViewById(R.id.Total);
        double total = 6.5 + 1.5*numTop + 4.0*checked;
        finalText.setText(total + "$");

        findViewById(R.id.buttonFinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                MainActivity.topRow.removeAllViews();
                MainActivity.bottomRow.removeAllViews();
                MainActivity.toppingsBar.setProgress(0);
                MainActivity.toppings.clear();
                MainActivity.load.setChecked(false);
            }
        });
    }
}
