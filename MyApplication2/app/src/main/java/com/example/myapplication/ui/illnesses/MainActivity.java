package com.example.myapplication.ui.illnesses;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Faq;
import com.example.myapplication.FaqAdapter;
import com.example.myapplication.FirstScreen;
import com.example.myapplication.Maladie;
import com.example.myapplication.R;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends Fragment {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    ListView ls;
    ArrayList<Maladie> arrayList;
    EditText searchFilter;
    public ImageView img;
    public TextView nomMaladie;
    public TextView desc;
    public TextView cause;
    public TextView sugg;
    public com.example.myapplication.DatabaseAccess databaseAccess;
    com.example.myapplication.MaladieAdapter maladieAdapter;
    private ActivityMainBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.activity_list_maladie,container,false);
        ls=root.findViewById(R.id.lst);
        searchFilter=root.findViewById(R.id.searchFilter);

        //SimpleCursorAdapter adapter=new SimpleCursorAdapter(ListMaladie.this,R.layout.items,c,
        // new String[] {c.getColumnName(5)},
        // new int[] {R.id.ll,R.id.nom,R.id.img},1);
        //  ArrayAdapter adapter
        //ls.setAdapter(adapter);

        maladieAdapter=new com.example.myapplication.MaladieAdapter(getContext(),R.layout.items,arrayList);
        ls.setAdapter(maladieAdapter);
//        searchFilter.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                (com.example.myapplication.ListMaladie.this).maladieAdapter.getFilter().filter(charSequence);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Bundle b= new Bundle();
                b.putString("nom_maladie",arrayList.get(i).getNom_maladie());
                b.putString("Description",arrayList.get(i).getDescription());
                b.putString("Suggestion",arrayList.get(i).getSuggestion());
                b.putString("Cause",arrayList.get(i).getCauses());
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                Bitmap bm=arrayList.get(i).getImage();
                bm.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] bt=stream.toByteArray();
                b.putByteArray("img",bt);
                createIllnessDialog(b);
//                intent.putExtras(b);
//                startActivity(intent);
//                ds.setArguments(b);
//                fragmentManager.setFragmentResult("requestKey",b);
//                fragmentManager.beginTransaction().replace(R.id.list_container, ds).commit();
//                fragmentManager.beginTransaction().add(ds.getParentFragment(),"this").commit();
//                fragmentManager.beginTransaction().remove(MainActivity.this).commit();
            }
        });

        return root;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList=new ArrayList<>();
        databaseAccess= com.example.myapplication.DatabaseAccess.getInstance(getActivity().getApplicationContext());
        databaseAccess.open();
        arrayList= databaseAccess.getAllMaladie();
    }
    public void createIllnessDialog(Bundle b){
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View Illness = getLayoutInflater().inflate(R.layout.activity_description_maladie,null);
        img=Illness.findViewById(R.id.img);
        nomMaladie=Illness.findViewById(R.id.nomMaladie);
        desc=Illness.findViewById(R.id.desc);
        cause=Illness.findViewById(R.id.cause);
        sugg=Illness.findViewById(R.id.sugg);

//        Bundle b= getActivity().getIntent().getExtras();
        byte[] bt=b.getByteArray("img");
        String base64= Base64.getEncoder().encodeToString(bt);
        byte[] decodeString=Base64.getDecoder().decode(base64);
        Bitmap bm= BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        img.setImageBitmap(bm);
        nomMaladie.setText(b.getString("nom_maladie"));
        desc.setText(b.getString("Description"));
        cause.setText(b.getString("Cause"));
        sugg.setText(b.getString("Suggestion"));
        dialogBuilder.setView(Illness);
        dialog = dialogBuilder.create();
        dialog.show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        databaseAccess.close();
    }
}