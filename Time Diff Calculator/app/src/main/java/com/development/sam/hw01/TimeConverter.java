/*
Homework 1
TimeConverter.java
Samantha Downing
Sai Manohar Yerra
 */
package com.development.sam.hw01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeConverter extends AppCompatActivity implements View.OnClickListener {

    public EditText utcHour;
    public EditText utcMinute;
    public TextView result;
    public TextView warning;
    public LinearLayout buttonLayout;
    public LinearLayout radioLayout;
    public Button buttonEST;
    public Button buttonCST;
    public Button buttonMST;
    public Button buttonPST;
    public Button buttonClear;
    public Button buttonConvert;
    public RadioGroup radioGroup;
    public RadioButton radioClear;
    public int hour;
    public int min;
    public Date utcDate;
    public String resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeconverter);

        utcHour = (EditText) findViewById(R.id.hours);
        utcMinute = (EditText) findViewById(R.id.minutes);
        result = (TextView) findViewById(R.id.result);
        warning = (TextView) findViewById(R.id.warning);
        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
        radioLayout = (LinearLayout) findViewById(R.id.radioLayout);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioClear = (RadioButton) findViewById(R.id.radioButtonClearAll);
        buttonEST = (Button) findViewById(R.id.buttonEST);
        buttonCST = (Button) findViewById(R.id.buttonCST);
        buttonMST = (Button) findViewById(R.id.buttonMST);
        buttonPST = (Button) findViewById(R.id.buttonPST);
        buttonClear = (Button) findViewById(R.id.buttonClearAll);
        buttonConvert = (Button) findViewById(R.id.buttonConvert);
    }

    public void onClick(View v) {
        if (v == buttonEST) {
            toEST();
        }
        if (v == buttonCST) {
            toCST();
        }
        if (v == buttonMST) {
            toMST();
        }
        if (v == buttonPST) {
            toPST();
        }
        updateResult(v);
    }

    public void convertClick(View v){
        int selection = radioGroup.getCheckedRadioButtonId();
        String selectionName =  getResources().getResourceEntryName(selection);

        if (selectionName.equals("radioButtonEST")) {
            toEST();
        }
        if (selectionName.equals("radioButtonCST")) {
            toCST();
        }
        if (selectionName.equals("radioButtonMST")) {
            toMST();
        }
        if (selectionName.equals("radioButtonPST")) {
            toPST();
        }
        updateResult(v);

    }

    public void toggleButtons(View v){
        ToggleButton t = (ToggleButton) v;
        clearInput();
        if(t.isChecked()) {
            RadioButton est = (RadioButton) findViewById(R.id.radioButtonEST);
            est.setChecked(true);
            radioLayout.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            buttonLayout.setVisibility(View.GONE);
            radioLayout.setVisibility(View.VISIBLE);
        }
    }

    public boolean hasValidInput(){
        String hourText = utcHour.getText().toString();
        String minText = utcMinute.getText().toString();

        if(hourText == null || hourText.isEmpty() || minText == null || minText.isEmpty())
        {
            Toast.makeText(TimeConverter.this,"Please enter a number in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }


        String timeStr = hourText.concat(minText);
        for(int i = 0; i < timeStr.length(); i++){
            if(!Character.isDigit(timeStr.charAt(i))){
                Toast.makeText(TimeConverter.this,"Please use only numbers in all fields", Toast.LENGTH_SHORT).show();
                clearInput();
                return false;
            }
        }

        try
        {
            hour = Integer.parseInt(hourText);
            min = Integer.parseInt(minText);
            if(hour < 0 || hour > 23) {
                Toast.makeText(TimeConverter.this, "Please enter an hour between 0 and 23", Toast.LENGTH_SHORT).show();
                clearInput();
                return false;
            }

            if(min < 0 || min > 59) {
                Toast.makeText(TimeConverter.this, "Please enter a minute between 0 and 59", Toast.LENGTH_SHORT).show();
                clearInput();
                return false;
            }

            String utcDateString = "" + hour + ":" + min;
            DateFormat utcFormat = new SimpleDateFormat("HH:mm");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            utcDate = utcFormat.parse(utcDateString);
            return true;
        }
        catch(NumberFormatException e)
        {
            Toast.makeText(TimeConverter.this,"Please enter only numeric characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        catch (ParseException e){
            return false;
        }
    }

    public void toEST(){
        if(hasValidInput()) {
            DateFormat estFormat = new SimpleDateFormat("HH:mm");
            estFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT+5"));

            String est = estFormat.format(utcDate);
            resultText = "EST : " + est;
        }
    }

    public void toCST(){
        if(hasValidInput()) {
            DateFormat cstFormat = new SimpleDateFormat("HH:mm");
            cstFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT+6"));

            String cst = cstFormat.format(utcDate);
            resultText = "CST : " + cst;
        }
    }

    public void toMST(){
        if(hasValidInput()) {
            DateFormat mstFormat = new SimpleDateFormat("HH:mm");
            mstFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"));

            String mst = mstFormat.format(utcDate);
            resultText = "MST : " + mst;
        }
    }

    public void toPST(){
        if(hasValidInput()) {
            DateFormat pstFormat = new SimpleDateFormat("HH:mm");
            pstFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT+8"));

            String pst = pstFormat.format(utcDate);
            resultText = "PST : " + pst;
        }
    }

    public void clearInput(){
        resultText = null;
        utcHour.setText(null);
        utcMinute.setText(null);
        result.setText(resultText);
        warning.setVisibility(View.INVISIBLE);

    }

    public void updateResult(View v){
        if(v == buttonClear || radioClear.isChecked()) {
            clearInput();
            return;
        }

        if(resultText != null) {
            String resultHour = resultText.split(":")[1];
            if (Integer.parseInt(resultHour.trim()) > hour) {
                warning.setVisibility(View.VISIBLE);
            }
            else
                warning.setVisibility(View.INVISIBLE);
            result.setText(resultText);
        }

    }

}
