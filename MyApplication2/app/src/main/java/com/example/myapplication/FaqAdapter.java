package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ActivityFaqAdapterBinding;

import java.util.ArrayList;
import java.util.List;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.MyViewHolder> {

        private List faqList;
        private Context context;
        public FaqAdapter(Context context,List faqList){
            this.faqList=faqList;
            this.context=context;
        }

        @Override
        // retournele nb total de cellule que contiendra la liste
        public int getItemCount() {
            return faqList.size();
        }
        @Override
        //// inflates the row layout from xml when needed
        //crée la vu d'une cellule
        // parent pour créer la vu et int pour spécifier le type de la cellule si on a plusieurrs type (orgnaisation differts)
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //pour créer un laouyt depuis un XML
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.activity_faq_adapter, parent, false);
            return new MyViewHolder(view);
        }
        @Override
        //appliquer Une donnée à une vue
        //// binds the data to the TextView... in each row
        public void onBindViewHolder(MyViewHolder holder, int position) {

            Faq faq = (Faq) faqList.get(position);
            holder.question.setText(faq.getQuestion());
            holder.answer.setText(faq.getAnswer());
            boolean isExpendable = ((Faq) faqList.get(position)).isExpandable();
            holder.expandableLayout.setVisibility(isExpendable? View.VISIBLE:View.GONE);
            holder.arrow.setImageResource(isExpendable? R.drawable.arrow_up:R.drawable.arrow_down);
        }

        //// stores and recycles views as they are scrolled off screen

        public class MyViewHolder extends RecyclerView.ViewHolder  {

            private final TextView question;
            private final TextView answer;

            //  private Pair<String, String> currentPair;
            private Faq currentFaq;
            private ImageView arrow;
            LinearLayout linearLayout;
            RelativeLayout expandableLayout;
            public MyViewHolder(final View itemView) {
                super(itemView);
                question = itemView.findViewById(R.id.question);
                answer = itemView.findViewById(R.id.answer);
                arrow = itemView.findViewById(R.id.arrow);

                linearLayout = itemView.findViewById(R.id.linear_layout);
                expandableLayout = itemView.findViewById(R.id.expandable);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        currentFaq = (Faq) faqList.get(getLayoutPosition());
                        currentFaq.setExpandable(!currentFaq.isExpandable());
//                        if(currentFaq.isExpandable()==true){
//                            arrow.setImageResource(R.drawable.arrow_up);
//                        }
//                        else{
//                            arrow.setImageResource(R.drawable.arrow_down);
//                        }
                        notifyItemChanged(getAdapterPosition());
                    }
                });

            }

        }

}