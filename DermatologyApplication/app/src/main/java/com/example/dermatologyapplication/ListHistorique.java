package com.example.dermatologyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class ListHistorique extends AppCompatActivity {
        ListView ls;
        EditText search;
        ArrayList<Historique> arrayList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_histo);
            ls=findViewById(R.id.list_histo);
            //search=findViewById(R.id.edit);
            DatabaseAccessHisto databaseAccessHisto=DatabaseAccessHisto.getInstance(getApplicationContext());

            databaseAccessHisto.open();
            arrayList=new ArrayList<>();

            arrayList=databaseAccessHisto.getAllHistorique();
            HistoriqueAdapter historiqueAdapter=new HistoriqueAdapter(ListHistorique.this,R.layout.item,arrayList);
            ls.setAdapter(historiqueAdapter);
            System.out.println(Arrays.toString(arrayList));


}}