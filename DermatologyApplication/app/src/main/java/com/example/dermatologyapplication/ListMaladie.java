package com.example.dermatologyapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListMaladie extends AppCompatActivity {
    ListView ls;
    ArrayList<Maladie> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_maladie);
        ls=findViewById(R.id.lst);
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());

        databaseAccess.open();



        //SimpleCursorAdapter adapter=new SimpleCursorAdapter(ListMaladie.this,R.layout.items,c,
               // new String[] {c.getColumnName(5)},
               // new int[] {R.id.ll,R.id.nom,R.id.img},1);
                      //  ArrayAdapter adapter
        //ls.setAdapter(adapter);
        arrayList=new ArrayList<>();

        arrayList=databaseAccess.getAllMaladie();
        MaladieAdapter maladieAdapter=new MaladieAdapter(ListMaladie.this,R.layout.items,arrayList);
        ls.setAdapter(maladieAdapter);

        databaseAccess.close();
    }
}