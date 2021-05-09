package com.projects.finalproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ord.db";

    public OrderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE = "CREATE TABLE " + OrderC.OrderEntry.TABLE_NAME + " ("
                + OrderC.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  OrderC.OrderEntry.COLUMN_NAME + " TEXT NOT NULL, "
                +  OrderC.OrderEntry.COLUMN_QUANTITY + " TEXT NOT NULL, "
                +  OrderC.OrderEntry.COLUMN_PRICE + " TEXT NOT NULL);";
        db.execSQL(SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
