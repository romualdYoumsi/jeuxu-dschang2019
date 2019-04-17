package com.example.jeuxu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.jeuxu.Inscription.Inscription;
import com.example.jeuxu.Maps.MapsLocation;

public class SplashScreen extends AppCompatActivity {
    private ImageView logo;
    private Animation appear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo=(ImageView)findViewById(R.id.imgsplash);
        appear= AnimationUtils.loadAnimation(this,R.anim.appear);
        logo.setAnimation(appear);
        final Intent i=new Intent(this, Choix_Connexion.class);

        Thread timer=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
