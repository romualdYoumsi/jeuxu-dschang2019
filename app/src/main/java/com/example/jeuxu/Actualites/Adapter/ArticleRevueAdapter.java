package com.example.jeuxu.Actualites.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jeuxu.Actualites.ArticlesRevueDesc;
import com.example.jeuxu.Actualites.Classes.Article_revue;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleRevueAdapter extends RecyclerView.Adapter<ArticleRevueViewHolder> {

    Context context;
    List<Article_revue>articleRevues;

    public ArticleRevueAdapter(Context context, List<Article_revue> articleRevues) {
        this.context = context;
        this.articleRevues = articleRevues;
    }

    @NonNull
    @Override
    public ArticleRevueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LinearLayout.inflate(context, R.layout.item_article_revue,null);
        return new ArticleRevueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleRevueViewHolder holder, final int position) {

        Picasso.with(context)
                .load(articleRevues.get(position).getLink())
                .into(holder.img_article_revue);
        holder.titre_article_revue.setText(articleRevues.get(position).getTitre());
        holder.desc_article_revue.setText(articleRevues.get(position).getDescription());
        holder.setItemClickListener(new IItemClickListener(){
            @Override
            public void onClick(View v) {
                Common.currentArticle=articleRevues.get(position);
                context.startActivity(new Intent(context, ArticlesRevueDesc.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleRevues.size();
    }
}
