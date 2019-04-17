package com.example.jeuxu.Securiter.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;

public class SecuriterViewHolder extends RecyclerView.ViewHolder  {

    IItemClickListener itemClickListener;
    TextView titre_securiter;

    public SecuriterViewHolder(View itemView) {
        super(itemView);

        titre_securiter=(TextView)itemView.findViewById(R.id.titre_securiter);


    }



}
