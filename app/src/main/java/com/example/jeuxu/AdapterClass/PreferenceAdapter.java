package com.example.jeuxu.AdapterClass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jeuxu.Classe.Preference;
import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PreferenceAdapter extends RecyclerView.Adapter<PreferenceViewHolder> {

    Context context;
    List<Preference>preferences;


    public PreferenceAdapter(Context context, List<Preference> preferences) {
        this.context = context;
        this.preferences = preferences;
            }

    @NonNull
    @Override
    public PreferenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(context).inflate(R.layout.item_discipline,null);

        return new PreferenceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PreferenceViewHolder holder, int position) {
       Picasso.with(context)
               .load(preferences.get(position).getLink())
               .into(holder.discipline_img);
       holder.discipline.setText(preferences.get(position).getName());
       holder.setItemClickListener(new IItemClickListener(){
           @Override
           public void onClick(View v) {
              if (holder.checkboxdiscipline.isChecked()){
                  holder.checkboxdiscipline.setChecked(false);
              }
              else {
                  holder.checkboxdiscipline.setChecked(true);
              }
           }
       });


    }

    @Override
    public int getItemCount() {
        return preferences.size();
    }
}
