package com.example.jeuxu.Actualites;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jeuxu.Decouvrir.Gallerie;
import com.example.jeuxu.R;

public class Actualites extends AppCompatActivity {

    LinearLayout classement_medail, result_compet, consulta_temps_fort, progra_discipline, article_revue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualites);


        setTitle("Actualite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        classement_medail = (LinearLayout) findViewById(R.id.classement_medail);
        result_compet = (LinearLayout) findViewById(R.id.result_compet);
        consulta_temps_fort = (LinearLayout) findViewById(R.id.consulta_temps_fort);
        progra_discipline = (LinearLayout) findViewById(R.id.progra_discipline);
        article_revue = (LinearLayout) findViewById(R.id.article_revue);

        classement_medail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Actualites.this, Gallerie.class);
                startActivity(i);
            }
        });

        progra_discipline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Actualites.this, Gallerie.class);
                startActivity(i);
            }
        });
        consulta_temps_fort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Actualites.this, Gallerie.class);
                startActivity(i);
            }
        });
        progra_discipline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Actualites.this, Gallerie.class);
                startActivity(i);
            }
        });
        article_revue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Actualites.this, Gallerie.class);
                startActivity(i);
            }
        });


    }
    Handler handler = new Handler();

    private Runnable updateData = new Runnable(){
        public void run(){
           // parseJson();
            handler.postDelayed(updateData,5000);
        }
    };



}
