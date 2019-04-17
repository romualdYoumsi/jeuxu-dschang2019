package com.example.jeuxu.Common;

import android.util.Log;

import com.example.jeuxu.Acceuil.Classe.news;
import com.example.jeuxu.Actualites.Classes.Article_revue;
import com.example.jeuxu.Classe.Buts;
import com.example.jeuxu.Classe.Client;
import com.example.jeuxu.Classe.Equipes;
import com.example.jeuxu.Classe.Rencontre;
import com.example.jeuxu.Classement.Classe.Classem;
import com.example.jeuxu.Securiter.Classe.dataSecuriter;
import com.example.jeuxu.Securiter.Classe.numero;

public class Common {
    public static Rencontre currentRencontre;
    public static dataSecuriter currentDataSecuriter;
    public static Buts currentButs;
    public static Equipes currentEquipe;
    public static Client currentClient;
    public static int LOCATION_REQ_CODE=456;
    public static Article_revue currentArticle;
    public static numero currentNumero;
    public static news currentNews;
    public static Classem currentClass;

    public static  String TABLE_NAME = "user_table";
    public static  String COL1 = "ID";
    public static  String COL2 = "NOM";
    public static  String COL3 = "PRENOM";
    public static  String COL4 = "SEXE";
    public static  String COL5 = "UNIVERSITE";
    public static  String COL6 = "LOCALISE";
    public static  String COL7 = "NUMERO";
    public static  String COL8 = "EMAIL";
    public static  String COL9 = "TOKEN";
    public static Long executeLongActionDuring7seconds(){

        Log.e("TAG", "Long action is starting...");
        Long endTime = System.currentTimeMillis() + 7000;
        while (System.currentTimeMillis() <  endTime) {
            //Loop during 7 secs hehehe...
        }
        Log.e("TAG", "Long action is finished !");

        return endTime;
    }

    public static String response="";
    public static Long success;
}
