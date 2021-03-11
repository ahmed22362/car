package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


public class MyDatabase extends SQLiteAssetHelper {
    public static final String DB_NAME = "cars";
    public static final int DB_VERSION = 1;

    public static final String CARS_CLN_ID = "id";
    public static final String CARS_CLN_COLOR = "color";
    public static final String CARS_CLN_MODEL = "model";
    public static final String CARS_CLN_DESCRIPTION= "description";
    public static final String CARS_CLN_IMAGE= "image";
    public static final String CARS_CLN_DPL= "dpl";

    public MyDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE "+DB_NAME+" ( "+CATS_CLN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , " +
//                ""+CATS_CLN_COLOR+" TEXT, "+CATS_CLN_NAME+" TEXT, "+CATS_CLN_IMAGE+" REAL) ");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+DB_NAME+" ");
//        onCreate(db);
//    }

}
