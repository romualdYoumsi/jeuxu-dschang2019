package com.example.jeuxu.Detail_Sport.Fragment_Detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jeuxu.Classe.Statistique_rencontre;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;

import java.util.List;

public class StatAdapter extends RecyclerView.Adapter<StatViewHolder> {

    Context mContext;
    List<Statistique_rencontre> statistiqueRencontres;


    public StatAdapter(Context mContext, List<Statistique_rencontre> statistiqueRencontres) {
        this.mContext = mContext;
        this.statistiqueRencontres = statistiqueRencontres;
    }

    @NonNull
    @Override
    public StatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LinearLayout.inflate(mContext, R.layout.item_statistique,null);

        return new StatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatViewHolder holder, int position) {
        if (Common.currentRencontre!=null){
            holder.valeur_joueur.setText(statistiqueRencontres.get(position).getID());
            holder.valeur_joueur1.setText(statistiqueRencontres.get(position).getValeur());
        }
    }

    @Override
    public int getItemCount() {
        return statistiqueRencontres.size();
    }
}
