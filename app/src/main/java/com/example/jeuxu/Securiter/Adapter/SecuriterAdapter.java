package com.example.jeuxu.Securiter.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jeuxu.Actualites.Classes.Article_revue;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;
import com.example.jeuxu.Securiter.Classe.numero;

import java.util.List;

public class SecuriterAdapter extends RecyclerView.Adapter<SecuriterViewHolder> {

    Context context;
    List<numero> numeros;

    public SecuriterAdapter(Context context, List<numero> numeros) {
        this.context = context;
        this.numeros = numeros;
    }

    @NonNull
    @Override
    public SecuriterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LinearLayout.inflate(context, R.layout.item_appel,null);
        return new SecuriterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecuriterViewHolder holder, final int position) {

        holder.titre_securiter.setText(numeros.get(position).getID());


    }

    @Override
    public int getItemCount() {
        return numeros.size();
    }
}
