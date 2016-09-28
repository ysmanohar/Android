package com.example.mannu.midterm;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mannu on 6/9/2016.
 */
public class Weathercast {

    public static Weather currentweather(String in)throws JSONException {
        Weather weather = new Weather();

        JSONObject root = new JSONObject(in);
          JSONArray list = root.getJSONArray("list");
         JSONObject objInsideList = list.getJSONObject(0);
         JSONArray forWeather = objInsideList.getJSONArray("weather");
         JSONObject main = objInsideList.getJSONObject("main");
         JSONObject desp = forWeather.getJSONObject(0);
         weather.setDescription(desp.getString("description"));
         weather.setTemperature(main.getDouble("temp"));
         weather.setPressure(main.getDouble("pressure"));
         weather.setHumidity(main.getDouble("humidity"));


        Log.d("humidity", String.valueOf(weather.getHumidity()));
        Log.d("temperature", String.valueOf(weather.getTemperature()));
        Log.d("humidity", String.valueOf(weather.getDescription()));
        Log.d("humidity", String.valueOf(weather.getPressure()));


        return weather;
    }

}
