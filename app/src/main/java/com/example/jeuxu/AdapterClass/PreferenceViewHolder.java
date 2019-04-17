package com.example.jeuxu.AdapterClass;

import android.content.ClipData;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;

public class PreferenceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView discipline;
    CheckBox checkboxdiscipline;
    IItemClickListener itemClickListener;
    ImageView discipline_img;

    public PreferenceViewHolder(View itemView) {
        super(itemView);
        discipline=(TextView)itemView.findViewById(R.id.discipline);
        discipline_img=(ImageView) itemView.findViewById(R.id.discipline_img);
        checkboxdiscipline=(CheckBox) itemView.findViewById(R.id.checkboxdiscipline);
        itemView.setOnClickListener(this);

    }
    public void setItemClickListener(IItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v);
    }

}
