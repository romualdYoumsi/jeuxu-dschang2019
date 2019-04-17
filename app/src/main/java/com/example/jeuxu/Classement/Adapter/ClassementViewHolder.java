package com.example.jeuxu.Classement.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeuxu.R;

public class ClassementViewHolder extends RecyclerView.ViewHolder{

    TextView txt_name_uni,txt_medail_or,txt_medail_arg,txt_medail_bron,txt_numero,txt_total;
    ImageView img_class;

    public ClassementViewHolder(View itemView) {
        super(itemView);
        txt_name_uni=(TextView)itemView.findViewById(R.id.txt_name_uni);
        txt_medail_or=(TextView)itemView.findViewById(R.id.txt_medail_or);
        txt_medail_arg=(TextView)itemView.findViewById(R.id.txt_medail_arg);
        txt_medail_bron=(TextView)itemView.findViewById(R.id.txt_medail_bron);
        txt_medail_bron=(TextView)itemView.findViewById(R.id.txt_medail_bron);
        txt_total=(TextView)itemView.findViewById(R.id.txt_total);
        img_class=(ImageView)itemView.findViewById(R.id.img_class);
    }
}
