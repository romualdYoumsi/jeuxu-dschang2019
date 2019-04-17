package com.example.jeuxu.Detail_Sport.Fragment_Detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jeuxu.R;

public class StatViewHolder_two extends RecyclerView.ViewHolder {

    TextView txt_stat_two;
    public StatViewHolder_two(View itemView) {
        super(itemView);
        txt_stat_two=(TextView)itemView.findViewById(R.id.txt_stat_two);
    }
}
