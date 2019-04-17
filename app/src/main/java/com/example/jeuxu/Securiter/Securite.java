package com.example.jeuxu.Securiter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jeuxu.R;

public class Securite extends AppCompatActivity  {

    LinearLayout securite_contacter,securite_consigne,securite_signaler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_securite);
        setTitle("Securiter");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        securite_contacter=(LinearLayout)findViewById(R.id.securite_contacter);
        securite_consigne=(LinearLayout)findViewById(R.id.securite_consigne);
        securite_signaler=(LinearLayout)findViewById(R.id.securite_signaler);

        securite_contacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ap=new Intent(Securite.this,Numero_Tel.class);
                startActivity(ap);
            }
        });

        securite_consigne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ip=new Intent(Securite.this,Consigne_securites.class);
                startActivity(ip);
            }
        });

        securite_signaler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ip=new Intent(Securite.this,SignalerAnomalie.class);
                startActivity(ip);
            }
        });

    }


}
