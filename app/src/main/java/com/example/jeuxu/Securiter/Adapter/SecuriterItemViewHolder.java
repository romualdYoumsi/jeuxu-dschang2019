package com.example.jeuxu.Securiter.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;

public class SecuriterItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView numero_securite, nom_securite;
    IItemClickListener itemClickListener;
    public SecuriterItemViewHolder(View itemView) {
        super(itemView);
        numero_securite = (TextView) itemView.findViewById(R.id.numero_securite);
        nom_securite = (TextView) itemView.findViewById(R.id.nom_securite);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v);
    }
}
