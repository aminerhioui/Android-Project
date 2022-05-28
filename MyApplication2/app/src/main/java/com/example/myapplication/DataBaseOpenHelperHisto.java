package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseOpenHelperHisto extends SQLiteAssetHelper {
    private static final String DATABASE_NAME="derm.db";
    private static final int DATABASE_VERSION=1;

    public DataBaseOpenHelperHisto(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public DataBaseOpenHelperHisto(Context context, String name, String storageDirectory, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, storageDirectory, factory, version);
    }
}
