package com.example.jeuxu.Detail_Sport.Fragment_Detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;

public class StatViewHolder extends RecyclerView.ViewHolder{

    TextView valeur_joueur1,valeur_joueur;






    public StatViewHolder(View itemView) {
        super(itemView);
        valeur_joueur=(TextView)itemView.findViewById(R.id.valeur);
        valeur_joueur1=(TextView)itemView.findViewById(R.id.valeur_joueur1);



    }

}
