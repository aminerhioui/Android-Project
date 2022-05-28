package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;

import androidx.core.content.ContextCompat;

import com.example.myapplication.databinding.ActivityFirstScreenBinding;

public class FirstScreen extends AppCompatActivity {
    private static final long SPLASH_SCREEN_TIMEOUT = 4000;
    private ActivityFirstScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFirstScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.black));



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(FirstScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);


    }
}