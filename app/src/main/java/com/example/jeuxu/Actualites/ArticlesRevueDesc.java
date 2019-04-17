package com.example.jeuxu.Actualites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeuxu.Common.Common;
import com.example.jeuxu.R;
import com.squareup.picasso.Picasso;

import io.reactivex.disposables.CompositeDisposable;

public class ArticlesRevueDesc extends AppCompatActivity {

    TextView desc_article_revue,titre_article_revue;
    ImageView img_article_revue;
    CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_revue_desc);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        desc_article_revue=(TextView)findViewById(R.id.desc_article_revue);
        titre_article_revue=(TextView)findViewById(R.id.titre_article_revue);
        img_article_revue=(ImageView) findViewById(R.id.img_article_revue);
        compositeDisposable=new CompositeDisposable();
        displayData();

    }

    private void displayData(){
        if (Common.currentArticle!=null){
            Picasso.with(this)
                    .load(Common.currentArticle.getLink())
                    .into(img_article_revue);

            titre_article_revue.setText(Common.currentArticle.getTitre());
            desc_article_revue.setText(Common.currentArticle.getDescription());
        }
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
