package com.example.mannu.inclass08;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by mannu on 6/21/2016.
 */
public class AppUtil {
    public static String prefix;
    public static String name;
    public static ArrayList<App> appArrayList = new ArrayList<>();
    public static App app = null;
    public static String text="";
    public  static int temp =0;
    static XmlPullParser newparser;
    static boolean flag = false;

    static public class AppPullParser{
        static ArrayList<App> parseApps(InputStream inputStream) throws XmlPullParserException, IOException {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream,"UTF-8");


            int event = parser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String temp = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        prefix = parser.getPrefix();
                        name = parser.getName();

                        if (parser.getName().equals("entry")) {
                            Log.d("Test", "entered");
                            app = new App();
                            flag=true;
                        }
                        else if(flag && parser.getName().equals("id")){
                            app.setId(parser.getAttributeValue(null,"id"));
                            Log.d("ID",app.getId());
                        }
                        else if(flag && parser.getName().equals("title")){
                            app.setAppTitle(parser.nextText().trim());
                            Log.d("title",app.getAppTitle());
                        }
                        else if(flag && parser.getName().equals("category")){
                            app.setCategory(parser.getAttributeValue(null,"term"));
                            Log.d("title",app.getAppTitle());
                        }
                        else if(flag && parser.getName().equals("releaseDate")){
                            app.setDate(parser.getAttributeValue(null,"label"));
                            Log.d("title",app.getAppTitle());
                        }
                        else if(flag &&parser.getName().equals("artist")){
                            app.setDevName(parser.nextText());
                            Log.d("dev",app.getDevName());
                        }
                        else if(flag &&(parser.getName().equals("image")) && (parser.getAttributeValue(null,"height")).equals("53")){
                            app.setSmallImage(parser.nextText().trim());
                            Log.d("small",app.getSmallImage());
                        }
                        else if(flag &&(parser.getName().equals("image")) && (parser.getAttributeValue(null,"height")).equals("100")){
                            app.setLargeImage(parser.nextText().trim());
                            Log.d("large",app.getLargeImage());
                        }
                        else if(flag && parser.getName().equals("link")){
                            app.setUrl(parser.getAttributeValue(null,"href"));
                            Log.d("URL",app.getUrl());
                        }
                        else if(flag && parser.getName().equals("price")){
                            app.setPrice(parser.getAttributeValue(null,"amount"));
                            Log.d("amount",app.getPrice());
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("entry")) {
                            Log.d("TitleTEst",app.getCategory());
                            appArrayList.add(app);
                            app = null;
                        }
                        break;

                    default:
                        break;
                }
                event = parser.next();

            }
            Log.d("Apptest", String.valueOf(appArrayList.size()));
            flag=false;
            return appArrayList;
        }

    }
}
