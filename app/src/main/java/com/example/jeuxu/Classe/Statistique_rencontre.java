package com.example.jeuxu.Classe;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Statistique_rencontre {
    private String ID;
    private String valeur;
    public List<Statistique_rencontre> statList=new ArrayList<>();
    private Statistique_rencontre statistiqueRencontre;

    public Statistique_rencontre(JSONObject jsonObject) {
        try {
            this.ID=jsonObject.getString("title");
            this.valeur=jsonObject.getString("value");

            JSONArray jsonArrayStat=jsonObject.getJSONArray("statistics");
            for (int i=0;i<jsonArrayStat.length();i++){
                statistiqueRencontre=new Statistique_rencontre(jsonArrayStat.getJSONObject(i));
                statList.add(statistiqueRencontre);
            }

        } catch (JSONException e) {
            Log.v("StatMessage",e.getMessage());
            e.printStackTrace();
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Statistique_rencontre getStatistiqueRencontre() {
        return statistiqueRencontre;
    }

    public void setStatistiqueRencontre(Statistique_rencontre statistiqueRencontre) {
        this.statistiqueRencontre = statistiqueRencontre;
    }
}
