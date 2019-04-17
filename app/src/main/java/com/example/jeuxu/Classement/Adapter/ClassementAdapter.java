package com.example.jeuxu.Classement.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeuxu.Classement.Classe.Classem;
import com.example.jeuxu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClassementAdapter extends RecyclerView.Adapter<ClassementViewHolder> {

    Context context;
    List<Classem>classemList;

    public ClassementAdapter(Context context, List<Classem> classemList) {
        this.context = context;
        this.classemList = classemList;
    }

    @NonNull
    @Override
    public ClassementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(context).inflate(R.layout.class_item,null);

        return new ClassementViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassementViewHolder holder, int position) {
    int nbre=position+1;
        holder.txt_numero.setText(""+nbre);
        holder.txt_medail_arg.setText(classemList.get(position).getArgent());
        holder.txt_medail_or.setText(classemList.get(position).getOr());
        holder.txt_medail_bron.setText(classemList.get(position).getBronze());
        holder.txt_name_uni.setText(classemList.get(position).getName_uni());
        int total=
                Integer.parseInt(classemList.get(position).getArgent().toString())+
                Integer.parseInt(classemList.get(position).getBronze().toString())+
                Integer.parseInt(classemList.get(position).getOr().toString())
                ;
        holder.txt_total.setText(""+total);
        Picasso.with(context)
                .load(classemList.get(position).getLink())
                .into(holder.img_class);
    }

    @Override
    public int getItemCount() {
        return classemList.size();
    }
}
