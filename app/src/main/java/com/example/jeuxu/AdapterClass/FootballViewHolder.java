package com.example.jeuxu.AdapterClass;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;

public class FootballViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    ImageView img_favorie,img_equipe_one,img_equipe_two,img_share;
    TextView txt_equipe_one,txt_score,txt_date_renc,txt_equipe_two,txt_stage_renc,txt_statut_renc;
    IItemClickListener itemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }


    public FootballViewHolder(View itemView) {
        super(itemView);

        img_favorie=(ImageView)itemView.findViewById(R.id.img_favoris);
        img_equipe_one=(ImageView)itemView.findViewById(R.id.img_equipe_one);
        img_equipe_two=(ImageView)itemView.findViewById(R.id.img_equipe_two);
        txt_equipe_one=(TextView) itemView.findViewById(R.id.txt_equipe_one);
        txt_equipe_two=(TextView) itemView.findViewById(R.id.txt_equipe_two);
        txt_date_renc=(TextView)itemView.findViewById(R.id.txt_date_renc);
        txt_stage_renc=(TextView)itemView.findViewById(R.id.txt_stade_renc);
        txt_score=(TextView)itemView.findViewById(R.id.txt_score_total);
        txt_statut_renc=(TextView)itemView.findViewById(R.id.txt_statut_renc);



        //setOnclickListner
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       itemClickListener.onClick(v);
 }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
