package com.example.mannu.inclass07;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mannu on 6/16/2016.
 */
public class AppList {
    static final String TABLENAME = "App";
    static final String COLUMN_ID = "_id";
    static final String APP_NAME = "App_Name";
    static final String DEV_NAME = "Dev_Name";
    static final String DATE = "Date";
    static final String PRICE = "Price";
    static final String CATEGORY = "Category";
    static final String STATUS = "Status";



    static public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + "(");
        sb.append(COLUMN_ID + " integer,");
        sb.append(APP_NAME + " text not null,");
        sb.append(DEV_NAME + " text not null,");
        sb.append(DATE + " text not null,");
        sb.append(PRICE + " text not null,");
        sb.append(CATEGORY + " text not null,");
        sb.append(STATUS + " text not null);");

        try {
            db.execSQL(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    static public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        AppList.onCreate(db);

    }
}
