package com.example.jeuxu.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.jeuxu.Classe.Joueur;
import com.example.jeuxu.Classe.Rencontre;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.Detail_Sport.Detail_sport;
import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class RencontreAdapter extends RecyclerView.Adapter<FootballViewHolder> {
    Context context;
    List<Rencontre> rencontreList;


    public RencontreAdapter(Context context, List<Rencontre> rencontreList) {
        this.context = context;
        this.rencontreList = rencontreList;
    }

    @NonNull
    @Override
    public FootballViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.football_item_layout, null);
        return new FootballViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FootballViewHolder holder, final int position) {
        Picasso.with(context)
                .load(rencontreList.get(position).getEquipesList().get(0).link)
                .into(holder.img_equipe_one);
        Picasso.with(context)
                .load(rencontreList.get(position).getEquipesList().get(1).link)
                .into(holder.img_equipe_two);
        holder.txt_equipe_one.setText(rencontreList.get(position).getEquipesList().get(0).nomEquipe);
        holder.txt_equipe_two.setText(rencontreList.get(position).getEquipesList().get(1).nomEquipe);

//        Log.d("txt", rencontreList.get(position).getEquipesList().get(0).nomEquipe);
        holder.txt_stage_renc.setText(rencontreList.get(position).getLieu().getNom_lieu());
        holder.txt_date_renc.setText(rencontreList.get(position).getDate());
        List<Joueur> joueurs1 = rencontreList.get(position).getEquipesList().get(0).joueurList;
        List<Joueur> joueurs2 = rencontreList.get(position).getEquipesList().get(1).joueurList;

        String score = getScroreJoueurs(joueurs1) + " - " + getScroreJoueurs(joueurs2);
        if (rencontreList.get(position).getCommencer() == 1) {
           holder.txt_statut_renc.setText(R.string.en_cour);
        } else if (rencontreList.get(position).getTerminer() == 1) {
            holder.txt_statut_renc.setText(R.string.terminer);
//            score += " (Terminer)";
        } else {
            holder.txt_statut_renc.setText(R.string.a_venir);
        }
        holder.txt_score.setText(score);


        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View v) {
                Common.currentRencontre = rencontreList.get(position);
                Intent i = new Intent(context, Detail_sport.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rencontreList.size();
    }

    private String getScroreJoueurs(List<Joueur> joueurs) {
        int a = 0;
        for (int i = 0; i < joueurs.size(); i++) {
            a += joueurs.get(i).nombre_buts.size();

        }
        return String.valueOf(a);
    }
//    public void onPause() {
//        super.onPause();
//        if (myHandler != null)
//            myHandler.removeCallbacks(myRunnable); // On arrete le callback
//    }


}
