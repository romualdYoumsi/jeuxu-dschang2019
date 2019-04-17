package com.example.jeuxu.Inscription;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.jeuxu.Choix_Connexion;
import com.example.jeuxu.Inscription.Fragment_inscription.Inscription_one;
import com.example.jeuxu.Inscription.Fragment_inscription.Inscription_two;
import com.example.jeuxu.MainActivity;
import com.example.jeuxu.R;

public class RegisterActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    private Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        frameLayout = (FrameLayout) findViewById(R.id.register_frame);

        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        frameLayout.setAnimation(animation);


        final Inscription_two inscription_two = new Inscription_two();
        final Inscription_one inscription_one = new Inscription_one();

        getSupportFragmentManager().beginTransaction().replace(R.id.register_frame, inscription_one).commit();

    }

}
