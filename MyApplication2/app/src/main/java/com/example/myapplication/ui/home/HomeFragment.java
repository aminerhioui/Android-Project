package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Faq;
import com.example.myapplication.FaqAdapter;
import com.example.myapplication.FirstScreen;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
//    private CardView diagnosis_card,historique_card,illnesses_card;
    private FragmentHomeBinding binding;
    private List<Faq> faqList;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = root.findViewById(R.id.recyclerview1);
        FaqAdapter faqAdapter = new FaqAdapter(getContext(),faqList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(faqAdapter);
        recyclerView.setHasFixedSize(true);
//        diagnosis_card = root.findViewById(R.id.diagnosis_card);
//        historique_card = root.findViewById(R.id.historique_card);
//        illnesses_card = root.findViewById(R.id.illnesses_card);
//        diagnosis_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.navigation_diagnosis);
//            }
//        });
//        historique_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.navigation_historique);
//            }
//        });
//        illnesses_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.navigation_illnesses);
//            }
//        });

        return root;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XmlPullParserFactory parserfactory;
        try {
            parserfactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserfactory.newPullParser();
            InputStream is = getContext().getAssets().open("databases/QnA.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(is,null);
            processParsing(parser);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
    private void processParsing(XmlPullParser parser) throws XmlPullParserException, IOException {
        faqList = new ArrayList<>();
        int eventType = parser.getEventType();
        Faq faqcurrent = null;
        while(eventType != XmlPullParser.END_DOCUMENT){
            String eltName = null;
            switch(eventType){
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();
                    if(eltName.equals("section")){
                        faqcurrent = new Faq();
                        faqList.add(faqcurrent);
                    }else if(faqcurrent!=null){
                        if(eltName.equals("question")){
                            faqcurrent.setQuestion(parser.nextText());
                        } else if(eltName.equals("answer")){
                            faqcurrent.setAnswer(parser.nextText());
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}