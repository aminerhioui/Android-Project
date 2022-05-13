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

public class MaladieAdapter extends ArrayAdapter<Maladie> {
    private Context mContext;
    private int mResource;
    private ArrayList<Maladie> arrayList;
    public MaladieAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Maladie> arrayList) {
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
        convertView=layoutInflater.inflate(R.layout.items,null);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.img);
        TextView txtView=convertView.findViewById(R.id.title);
        TextView txtDes=convertView.findViewById(R.id.sub);
        Maladie maladie=arrayList.get(position);
        if(imageView!=null) {
            imageView.setImageBitmap(maladie.getImage());
            txtView.setText(maladie.getNom_maladie());
            txtDes.setText(maladie.getDescription());
        }
        return convertView;
    }
}
