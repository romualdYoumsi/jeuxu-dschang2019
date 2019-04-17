package com.example.jeuxu.Securiter.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jeuxu.Common.Common;
import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;
import com.example.jeuxu.Securiter.Classe.dataSecuriter;

import java.util.List;

public class SecuriterItemAdapter extends RecyclerView.Adapter<SecuriterItemViewHolder> {

    Context context;
    List<dataSecuriter> securiters;

    public SecuriterItemAdapter(Context context, List<dataSecuriter> securiters) {
        this.context = context;
        this.securiters = securiters;
    }



    @NonNull
    @Override
    public SecuriterItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LinearLayout.inflate(context, R.layout.item_securiter_numero,null);

        return new SecuriterItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecuriterItemViewHolder holder, final int position) {
        holder.nom_securite.setText(securiters.get(position).getTitre());
        holder.numero_securite.setText(securiters.get(position).getNumero());
        holder.setItemClickListener(new IItemClickListener(){
            @Override
            public void onClick(View v) {
                Common.currentDataSecuriter=securiters.get(position);
                context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +Common.currentDataSecuriter.getNumero().toString())));
            }
        });


    }

    @Override
    public int getItemCount() {
        return securiters.size();
    }
}
