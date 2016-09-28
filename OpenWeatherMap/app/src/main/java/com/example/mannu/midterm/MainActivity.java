package com.example.mannu.midterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggle;
    public  static String  units="metric";
    String input;
    public  static String KEY1="input";
    public  static String KEY2="units";
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text= (EditText) findViewById(R.id.editText);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    if(isChecked()){
                input= String.valueOf(text.getText());
                Intent intent=new Intent(MainActivity.this,WeatherSummaryActivity.class);
                Log.d("Test",input);
                Log.d("Test",units);
                intent.putExtra("input",input);
                intent.putExtra("units",units);
                startActivity(intent);
//                }

            }
        });

       toggle= (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    units="imperial";
                }else{units="metric";}
                Log.d("Test",units);
            }

        });

    }

    private boolean isChecked() {
        if(input.length()!=0){
            return  true;
        }
        Toast.makeText(this,"Please enter valid input",Toast.LENGTH_LONG).show();
        return  false;
    }
}
