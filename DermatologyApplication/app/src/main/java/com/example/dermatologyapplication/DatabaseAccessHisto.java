package com.example.dermatologyapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DatabaseAccessHisto {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccessHisto instance;
    Cursor c;

    private DatabaseAccessHisto(Context context){

        this.openHelper=new DataBaseOpenHelper(context);

    }

    public static DatabaseAccessHisto getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccessHisto(context);
        }
        return instance;
    }

    public void open(){

        this.db=openHelper.getWritableDatabase();
    }
    public void close(){
        if(db!=null){
            this.db.close();
        }
    }



    public ArrayList<Maladie> getAllHistogramme(){
        ArrayList<Maladie> arrayList=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT * FROM Histograme",null);
        while(c.moveToNext()) {
            byte[] b=c.getBlob(5);
            if(b!=null) {
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                arrayList.add(new Maladie(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), objectBitmap));
            }

        }
        return arrayList;
    }

}
