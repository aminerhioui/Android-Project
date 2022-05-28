package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseAccessHisto {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccessHisto instance;
    Cursor c ;

    private DatabaseAccessHisto(Context context){

        this.openHelper=new DataBaseOpenHelperHisto(context);

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
    public void insertData(String Nom_maladie ,
            float Pourcentage ,
            Integer Confirmation,
            Bitmap Photo){
        SQLiteDatabase db = this.openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom_maladie",Nom_maladie);
        contentValues.put("pourcentage",Pourcentage);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        contentValues.put("image", bArray);


        db.insert("Historique", null, contentValues);

    }



    public ArrayList<Historique> getAllHistorique(){
        ArrayList<Historique> arrayList=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT * FROM Historique",null);
        while(c.moveToNext()) {
            byte[] b=c.getBlob(4);
            //if(b!=null) {
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

                arrayList.add(new Historique(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3),
                        objectBitmap));
                Log.d("MyApp",c.getInt(0)+"amine");
           // }

        }


        return arrayList;
    }

}
