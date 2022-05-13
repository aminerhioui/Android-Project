package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.myapplication.DataBaseOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c;

    private DatabaseAccess(Context context){

        this.openHelper=new DataBaseOpenHelper(context);

    }

    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);
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


    public String getAdress(String Name){
        c=db.rawQuery("select suggestion from Maladie where description ='"+Name+"'",new String[]{});
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext()){
            String nomMaladie=c.getString(0);
            buffer.append(""+nomMaladie);

        }
        return buffer.toString();
    }
    public ArrayList<Maladie> getAllMaladie(){
        ArrayList<Maladie> arrayList=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT * FROM Maladie",null);
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
