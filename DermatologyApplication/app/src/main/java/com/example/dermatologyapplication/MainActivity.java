package com.example.dermatologyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView rst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        rst=findViewById(R.id.rst);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ListMaladie.class);
                startActivity(intent);
                // create database access;
                //DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
                //databaseAccess.open();
                //String name="**";
                //String nomMaladie=databaseAccess.getAdress(name);
                //rst.setText(nomMaladie);
                //databaseAccess.close();


            }
        });





    }
}