package com.example.mannu.inclass07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

/**
 * Created by mannu on 6/16/2016.
 */
public class DatabaseDataManager {
    private Context mContext;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private NoteDAO noteDAO;
    private  AppList table;

    public DatabaseDataManager(Context mContext) {
        this.mContext = mContext;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = dbOpenHelper.getWritableDatabase();
        table = new AppList();
        table.onCreate(db);
        noteDAO = new NoteDAO(db);
    }

    public  void close(){
        if(db != null){
            db.close();
        }
    }

    public  NoteDAO getnoteDAO(){
        return this.noteDAO;
    }

    public  long saveNote(App note){
        return this.noteDAO.save(note);
    }

    public boolean updateNote(App app){
        return  this.noteDAO.update(app);
    }

    public boolean deleteNote(App note){
        return this.noteDAO.delete(note);
    }

    public List<App> getAllNotes(){
        return  noteDAO.getAll();
    }

    public boolean check(String appTitle) {
        Log.d("Result",appTitle);
        return noteDAO.check(appTitle);
    }
}
