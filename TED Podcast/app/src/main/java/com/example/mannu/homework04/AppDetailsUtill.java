package com.example.mannu.homework04;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by mannu on 6/21/2016.
 */
public class AppDetailsUtill {
    public static class ParseAppDetails extends DefaultHandler {
        ArrayList<AppData> appDataList;
        AppData appData;
        int i=0;
        int entry = 0;
        StringBuilder sb;
        int image = 0;

        public static ArrayList<AppData> ParseXml(InputStream in) throws IOException, SAXException {
            ParseAppDetails parse = new ParseAppDetails();
            Xml.parse(in, Xml.Encoding.UTF_8,parse);
            Log.d("demo","sent for parsing");
            return parse.getAppDataList();
        }

        public ArrayList<AppData> getAppDataList() {
            return appDataList;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("item")){
                appData = new AppData();
                entry =1;
            }
            else if (localName.equals("image") && entry!=0) {
                appData.setImage_URL(attributes.getValue("href"));
            } else if (localName.equals("enclosure") && entry!=0) {
                appData.setPodcast(attributes.getValue("url"));
            }


        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName.equals("item")) {
                appDataList.add(appData);
                i++;
            }else if( localName.equals("title") && entry!=0){
                appData.setTitle(sb.toString().trim());
            }else if( localName.equals("description") && entry!=0){
                appData.setDescription(sb.toString().trim());
            }else if( localName.equals("pubDate") && entry!=0){

                String string =sb.toString().trim();
                String[] parts = string.split(" ");
                string="";
                for(i =0 ; i<4;i++){
                    string = string.concat(parts[i]);
                    string = string.concat(" ");
                }
                appData.setDate(string);
            }else if( localName.equals("duration") && entry!=0 ) {

                appData.setDuration(sb.toString().trim());
                Log.d("duration", appData.getDuration());
            }
            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            sb.append(ch, start, length);
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            sb =new StringBuilder();
            appDataList = new ArrayList<>();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }



    }

}
