package com.example.jeuxu.Actualites.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeuxu.Interface.IItemClickListener;
import com.example.jeuxu.R;

public class ArticleRevueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    IItemClickListener itemClickListener;
    TextView desc_article_revue,titre_article_revue;
    ImageView img_article_revue;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ArticleRevueViewHolder(View itemView) {
        super(itemView);
        img_article_revue=(ImageView)itemView.findViewById(R.id.img_article_revue);
        desc_article_revue=(TextView) itemView.findViewById(R.id.desc_article_revue);
        titre_article_revue=(TextView)itemView.findViewById(R.id.titre_article_revue);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v);
    }
}
