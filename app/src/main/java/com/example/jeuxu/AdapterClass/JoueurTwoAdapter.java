package com.example.jeuxu.AdapterClass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jeuxu.Classe.Joueur;
import com.example.jeuxu.Classe.Rencontre;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.R;

import java.util.List;

public class JoueurTwoAdapter extends RecyclerView.Adapter<JoueurViewHolder> {
    Context mContext;

    List<Joueur> joueurs;
    public JoueurTwoAdapter(Context mContext, List<Joueur> joueurs) {
        this.mContext = mContext;
        this.joueurs=joueurs;
    }

    @NonNull
    @Override
    public JoueurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LinearLayout.inflate(mContext, R.layout.item_joueur, null);
        return new JoueurViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JoueurViewHolder holder, int position) {
        if (Common.currentRencontre != null) {
            holder.txt_nom_joueur.setText(joueurs.get(position).nom);
            holder.txt_post_joueur.setText(joueurs.get(position).poste);

        }

    }

    @Override
    public int getItemCount() {
        return joueurs.size() ;
    }
}
