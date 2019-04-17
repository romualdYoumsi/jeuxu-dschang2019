package com.example.jeuxu.Acceuil.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jeuxu.Acceuil.Classe.news;
import com.example.jeuxu.Acceuil.DetailActu;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActuAdapter extends RecyclerView.Adapter<ActuViewHolder> {

    Context context;
    List<news> newsList;


    public ActuAdapter(Context context, List<news> newsList) {
        this.context = context;
        this.newsList = newsList;
    }


    @NonNull
    @Override
    public ActuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LinearLayout.inflate(context, R.layout.acceuil_item,null);
        return new ActuViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ActuViewHolder holder, final int position) {
       Picasso.with(context)
                .load(newsList.get(position).getLink())
                .into(holder.actuImage);
       holder.txt_titre.setText(newsList.get(position).getTitre());
       holder.setiItemClickListener(new IItemClickListener(){
           @Override
           public void onClick(View v) {
               Common.currentNews=newsList.get(position);
               Intent i=new Intent(context, DetailActu.class);
               i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(i);
           }
       });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
