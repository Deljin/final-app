package com.example.attendence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "class.db";
    public static final String TABLE_NAME = "class_table";
    public static final String COL_1 = "ROLLO";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "BRANCH";
    public static final String COL_4 = "ATTENDANCE";
    public static final String COL_5 = "SUBJECT1";
    public static final String COL_6 = "SUBJECT2";
    public static final String COL_7 = "SUBJECT3";
    public static final String COL_8 = "EMAIL_ID";
    public static final String COL_9 = "PHONE_NO";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ROLLO INTEGER,NAME TEXT,BRANCH TEXT,ATTENDANCE INTEGER,SUBJECT1 INTEGER,SUBJECT2 INTEGER,SUBJECT3 INTEGER,EMAIL_ID TEXT,PHONE_NO INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String roll,String name,String branch,String atten,String subj1,String subj2,String subj3,String email,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,roll);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,branch);
        contentValues.put(COL_4,atten);
        contentValues.put(COL_5,subj1);
        contentValues.put(COL_6,subj2);
        contentValues.put(COL_7,subj3);
        contentValues.put(COL_8,email);
        contentValues.put(COL_9,phone);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String roll,String name,String branch,String atten,String subj1,String subj2,String subj3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,roll);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,branch);
        contentValues.put(COL_4,atten);
        contentValues.put(COL_5,subj1);
        contentValues.put(COL_6,subj2);
        contentValues.put(COL_7,subj3);
        db.update(TABLE_NAME, contentValues, "ROLLO = ?",new String[] { roll });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ROLLO = ?",new String[] {id});
    }
}
