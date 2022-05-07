package com.example.dermatologyapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.text.Editable;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;

public class ListMaladie extends AppCompatActivity {
    ListView ls;
    EditText search;
    ArrayList<Maladie> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_maladie);
        ls=findViewById(R.id.lst);
        search=findViewById(R.id.edit);
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
        ls.setTextFilterEnabled(true);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                //MaladieAdapter.getFilter().filter(s.toString());

            }


            @Override
            public void afterTextChanged(Editable editable) {

            }})


        ;

        databaseAccess.close();
    }
}