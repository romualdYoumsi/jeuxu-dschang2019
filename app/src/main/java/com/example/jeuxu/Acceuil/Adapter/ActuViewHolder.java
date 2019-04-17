package com.example.jeuxu.Acceuil.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;

public class ActuViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView actuImage;
    TextView txt_titre;
    IItemClickListener iItemClickListener;

    public ActuViewHolder(View itemView) {
        super(itemView);
        actuImage=(ImageView) itemView.findViewById(R.id.actuImage);
        txt_titre=(TextView) itemView.findViewById(R.id.txt_titre);
        itemView.setOnClickListener(this);

    }

    public void setiItemClickListener(IItemClickListener iItemClickListener) {
        this.iItemClickListener = iItemClickListener;
    }

    @Override
    public void onClick(View v) {
        iItemClickListener.onClick(v);
    }
}
