package com.example.jeuxu.AdapterClass;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jeuxu.R;

public class JoueurViewHolder extends RecyclerView.ViewHolder {

    TextView txt_nom_joueur,txt_post_joueur;


    public JoueurViewHolder(View itemView) {
        super(itemView);
        txt_nom_joueur=(TextView)itemView.findViewById(R.id.txt_nom_joueur);
        txt_post_joueur=(TextView)itemView.findViewById(R.id.txt_post_joueur);
    }
}
