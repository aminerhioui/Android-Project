package com.example.myapplication.ui.historique;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DatabaseAccessHisto;
import com.example.myapplication.Historique;
import com.example.myapplication.HistoriqueAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHistoriqueBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class HistoriqueFragment extends Fragment {
    ListView ls;
    EditText search;
    ArrayList<Historique> arrayList;
    private FragmentHistoriqueBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historique,container,false);
        ls=root.findViewById(R.id.list_histo);
        //search=findViewById(R.id.edit);
        DatabaseAccessHisto databaseAccessHisto=DatabaseAccessHisto.getInstance(getActivity().getApplicationContext());

        databaseAccessHisto.open();
        arrayList=new ArrayList<>();

        arrayList=databaseAccessHisto.getAllHistorique();
        HistoriqueAdapter historiqueAdapter=new HistoriqueAdapter(getContext(),R.layout.item,arrayList);
        ls.setAdapter(historiqueAdapter);
//        System.out.println(Arrays.toString(arrayList));

       return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}