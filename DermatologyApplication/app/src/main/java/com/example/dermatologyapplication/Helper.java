package com.example.dermatologyapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {
    public Helper(@Nullable Context context) {
        super(context, "histor", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE historique(id_historique INTEGER PRIMARY KEY, nom_maladie TEXT, pourcentage INTEGER, confirmation INTEGER,photo BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertHisto(Historique h){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv= new ContentValues();
        cv.put("nom_maladie",h.getNom_maladie());
        cv.put("pourcentage",h.getPourcentage());
        cv.put("confirmation",h.getConfirmation());

        db.insert("historique", null,cv);
        db.close();
    }
    public void deleteHisto(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("historique","id_historique",new String[]{String.valueOf(id)} );
        db.close();

    }
    public Cursor getAllHisto(){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("Select * FROM Historique",null);
        return c;
    }
}
