package com.example.jeuxu.Detail_Sport.Fragment_Detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jeuxu.Classe.Statistique_rencontre;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.R;

import java.util.List;

public class StatAdapter_two extends RecyclerView.Adapter<StatViewHolder_two> {

    Context mContext;
    List<Statistique_rencontre>statistiqueRencontres;

    public StatAdapter_two(Context mContext, List<Statistique_rencontre> statistiqueRencontres) {
        this.mContext = mContext;
        this.statistiqueRencontres = statistiqueRencontres;
    }

    @NonNull
    @Override
    public StatViewHolder_two onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LinearLayout.inflate(mContext, R.layout.item_stat_two,null);
        return new StatViewHolder_two(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatViewHolder_two holder, int position) {
        if (Common.currentRencontre!=null){
            holder.txt_stat_two.setText(statistiqueRencontres.get(position).getValeur());
        }
    }

    @Override
    public int getItemCount() {
        return statistiqueRencontres.size();
    }
}
