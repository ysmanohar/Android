//Samantha Downing
//InClass2
//MainActivity.java

package com.development.sam.inclass2a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity{

    public final DecimalFormat df = new DecimalFormat("#.00");
    public EditText val;
    public TextView result;
    public double usd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val = (EditText) findViewById(R.id.editText);
        result = (TextView) findViewById(R.id.textView);
    }

    public boolean hasValidInput(){
        String valueText = val.getText().toString();
        if(valueText == null || valueText.isEmpty())
        {
            Toast.makeText(MainActivity.this,"Please enter a number", Toast.LENGTH_SHORT).show();
            return false;
        }

        try
        {
            usd = Double.parseDouble(valueText);
            return true;
        }
        catch(NumberFormatException e)
        {
            Toast.makeText(MainActivity.this,"Please enter only numeric characters", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void toEUR(View v){
        if(hasValidInput()) {
            double conversion = usd * 0.849282;
            String resultText = val.getText().toString() + " USD = " + df.format(conversion) + " EUR";

            result.setText(resultText);
        }
    }

    public void toCAD(View v){
        if(hasValidInput()) {
            double conversion = usd * 1.19;
            String resultText = val.getText().toString() + " USD = " + df.format(conversion) + " CAD";

            result.setText(resultText);

        }
    }

    public void toGBP(View v){
        if(hasValidInput()) {
            double conversion = usd * 0.65;
            String resultText = val.getText().toString() + " USD = " + df.format(conversion) + " GBP";

            result.setText(resultText);
        }
    }

    public void toJPY(View v){
        if(hasValidInput()) {
            double conversion = usd * 117.62;
            String resultText = val.getText().toString() + " USD = " + df.format(conversion) + " JPY";

            result.setText(resultText);
        }
    }

    public void clearAll(View v){
        val.setText(null);
        result.setText(R.string.result);
    }

}
