package com.example.mannu.inclass07;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mannu on 6/16/2016.
 */
public class NoteDAO {
    private SQLiteDatabase db;

    public NoteDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public  long save(App note){
        ContentValues values = new ContentValues();
        values.put(AppList.COLUMN_ID,note.getId());
        values.put(AppList.APP_NAME,note.getAppTitle());
        values.put(AppList.DEV_NAME,note.getDevName());
        values.put(AppList.PRICE,note.getPrice());
        values.put(AppList.CATEGORY,note.getCategory());
        values.put(AppList.DATE,note.getDate());
        values.put(AppList.STATUS,note.getStatus());
        return  db.insert(AppList.TABLENAME,null,values);
    }

    public boolean update(App note){
        ContentValues values = new ContentValues();
        values.put(AppList.COLUMN_ID,note.getId());
        values.put(AppList.APP_NAME,note.getAppTitle());
        values.put(AppList.DEV_NAME,note.getDevName());
        values.put(AppList.PRICE,note.getPrice());
        values.put(AppList.CATEGORY,note.getCategory());
        values.put(AppList.DATE,note.getDate());
        values.put(AppList.STATUS,note.getStatus());

        if(db.update(AppList.TABLENAME,values,AppList.APP_NAME+"=?",new String[]{note.getAppTitle()+""})>0){
            return  true;
        }
        else {return false;}
    }

    public boolean delete(App note){
        return db.delete(AppList.TABLENAME,AppList.APP_NAME+"=?",new String[]{note.getAppTitle()+""})>0;
    }

    public List<App> getAll(){
        List<App> notes = new ArrayList<App>();
        Cursor c=db.query(AppList.TABLENAME,new String[]{AppList.COLUMN_ID,AppList.APP_NAME,AppList.DEV_NAME,AppList.DATE,AppList.PRICE,AppList.CATEGORY,AppList.STATUS},null,null,null,null,null);
        if(c !=null &&c.moveToFirst()){
            do{ App note = buildNotefromCursor(c);
                notes.add(note);
            }while (c.moveToNext());

            if(!c.isClosed()){
                c.close();
            }
        }
        return notes;
    }

    private App buildNotefromCursor(Cursor c){
        App note=null;
        if(c != null){
            note= new App();
            note.setId(c.getString(0));
            note.setAppTitle(c.getString(1));
            note.setDevName(c.getString(2));
            note.setDate(c.getString(3));
            note.setPrice(c.getString(4));
            note.setCategory(c.getString(5));
        }

        return  note;
    }

    public boolean check(String appTitle) {
        Log.d("Result",appTitle);
        Log.d("Name",appTitle);
        App note=null;
        Cursor c=db.query(AppList.TABLENAME,new String[]{AppList.COLUMN_ID,AppList.APP_NAME,AppList.DEV_NAME,AppList.DATE,AppList.PRICE,AppList.CATEGORY,AppList.STATUS},AppList.APP_NAME+"=?",new String[]{appTitle+""},null,null,null,null);

        if(c != null && c.moveToFirst()){
            note =buildNotefromCursor(c);
            if(!c.isClosed()){
                c.close();
            }
            return  true;
        }else {
            return false;
        }
}}
