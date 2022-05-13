package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HistoriqueAdapter extends ArrayAdapter<Historique> {

    private Context mContext;
    private int mResource;
    private ArrayList<Historique> arrayList;
    public HistoriqueAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Historique> arrayList) {
        super(context, resource, arrayList);
        this.mContext=context;
        this.mResource=resource;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        //convertView=layoutInflater.inflate(mResource,parent,false);
        convertView=layoutInflater.inflate(R.layout.item,null);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.photo);
        TextView txtView=convertView.findViewById(R.id.nomM);
        TextView txtDes=convertView.findViewById(R.id.pourcentage);
        Historique historique=arrayList.get(position);
        if(imageView!=null) {
            imageView.setImageBitmap(historique.getPhoto());
            txtView.setText(historique.getNom_maladie());
            txtDes.setText(historique.getPourcentage());
        }
        return convertView;
}}
