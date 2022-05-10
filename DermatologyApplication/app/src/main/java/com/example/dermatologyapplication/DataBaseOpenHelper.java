package com.example.dermatologyapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME="derm.db";
    private static final int DATABASE_VERSION=1;

    public DataBaseOpenHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

}
