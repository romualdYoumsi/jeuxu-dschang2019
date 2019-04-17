package com.example.jeuxu.Decouvrir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jeuxu.Maps.MapsLocation;
import com.example.jeuxu.R;

public class Decouvrir_site extends AppCompatActivity {

    LinearLayout decouvrir_site,decouvrir_ville,decouvrir_offre,decouvrir_gallerie,decouvrir_transport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decouvrir_site);

        setTitle("Decouvrir site");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        decouvrir_site=(LinearLayout)findViewById(R.id.decouvrir_site);
        decouvrir_ville=(LinearLayout)findViewById(R.id.decouvrir_ville);
        decouvrir_offre=(LinearLayout)findViewById(R.id.decouvrir_offre);
        decouvrir_gallerie=(LinearLayout)findViewById(R.id.decouvrir_gallerie);
        decouvrir_transport=(LinearLayout)findViewById(R.id.decouvrir_transport);

        decouvrir_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Decouvrir_site.this, MapsLocation.class);
                startActivity(i);
            }
        });

        decouvrir_ville.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Decouvrir_site.this, MapsLocation.class);
                startActivity(i);
            }
        });

        decouvrir_offre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Decouvrir_site.this, Gallerie.class);
                startActivity(i);
            }
        });
        decouvrir_gallerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Decouvrir_site.this, Transport.class);
                startActivity(i);
            }
        });
        decouvrir_transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Decouvrir_site.this, Offre_touristique.class);
                startActivity(i);
            }
        });

    }
}
