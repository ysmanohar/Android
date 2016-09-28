package com.example.mannu.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherSummaryActivity extends AppCompatActivity {
    public  static String KEY1="input";
    public  static String KEY2="units";
    public  static String web;
    Weather data;
    TextView temp;
    Weather weather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_summary);

        if(getIntent().getExtras()!=null){
            String input=getIntent().getExtras().getString(KEY1);
            String units=getIntent().getExtras().getString(KEY2);

            web="http://api.openweathermap.org/data/2.5/forecast/city?q="+input+"&units="+units+"&APPID=0286d60e3d0b9356d8cff95a4e3e4c0d";
            Log.d("web",web);
            new GetData().execute(web);
            if(getIntent().getExtras()!= null){
                 data = (Weather) getIntent().getExtras().getSerializable("KEY");
                    }
            finish();
        }

        temp= (TextView) findViewById(R.id.temp);
        temp.setText((int) data.getTemperature());

        TextView pressure =(TextView) findViewById(R.id.pressure);
        pressure.setText((int) data.getPressure());

        TextView humidity = (TextView) findViewById(R.id.humidity);
        humidity.setText((int) data.getHumidity());

        TextView desc = (TextView) findViewById(R.id.weather);
        desc.setText(data.getDescription());



    }
}
