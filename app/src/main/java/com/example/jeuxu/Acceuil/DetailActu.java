package com.example.jeuxu.Acceuil;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeuxu.Common.Common;
import com.example.jeuxu.R;
import com.squareup.picasso.Picasso;

public class DetailActu extends AppCompatActivity {

TextView description_article,txt_titre;
ImageView actuImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_actu);

        description_article=(TextView)findViewById(R.id.description_article);
        txt_titre=(TextView)findViewById(R.id.txt_titre);
        actuImage=(ImageView)findViewById(R.id.actuImage);

        display();


    }
    public void display(){
        if (Common.currentNews!=null){
            description_article.setText(Common.currentNews.getDescription());
            txt_titre.setText(Common.currentNews.getTitre());
            Picasso.with(this)
                    .load(Common.currentNews.getLink())
                    .into(actuImage);

        }
    }
}
