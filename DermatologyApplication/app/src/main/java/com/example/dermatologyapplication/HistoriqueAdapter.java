package com.example.dermatologyapplication;


import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class HistoriqueAdapter {

    private Context mContext;
    private int mResource;
    private ArrayList<Historique> arrayList;
    public HistoriqueAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Historique> arrayList) {
        super();
        this.mContext=context;
        this.mResource=resource;
        this.arrayList=arrayList;
    }
}
