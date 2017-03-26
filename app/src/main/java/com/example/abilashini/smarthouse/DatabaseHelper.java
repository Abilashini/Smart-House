package com.example.abilashini.smarthouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abilashini on 1/3/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_USERID = "id";
    private static final String COLUMN_USERNAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table user(id integer primary key not null, name String not null, password String not null);";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }


    public void insertUser(User u){
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String query = "select * from user";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_USERID, count);
        values.put(COLUMN_USERNAME, u.getUserName());
        values.put(COLUMN_PASSWORD,u.getPassword() );
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String uname){
        db=this.getReadableDatabase();
        String query = "select name,password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b="not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a.equals(uname)){
                    b=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);


    }
}

