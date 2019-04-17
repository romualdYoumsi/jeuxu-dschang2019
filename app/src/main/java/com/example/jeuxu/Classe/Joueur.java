package com.example.jeuxu.Classe;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    public String ID;
    public String nom;
    public String prenom;
    public String date_naissance;
    public String sexe;
    public List<Buts> nombre_buts=new ArrayList<>();
    public String poste;





    public Joueur(JSONObject jsonObject) {
        this.ID = "0";
        try {
            this.nom = jsonObject.getString("nom_joueur");
            this.prenom = jsonObject.getString("prenom_joueur");
            this.sexe = jsonObject.getString("sexe");
            this.poste = jsonObject.getString("poste");
            Log.d("JoueursJson",this.poste);
            JSONArray jsonArrayButs = jsonObject.getJSONArray("but");
            Log.d("JoueursJson",String.valueOf(jsonArrayButs.length()));

            for (int i = 0; i < jsonArrayButs.length(); i++) {
                Buts buts = new Buts(jsonArrayButs.getJSONObject(i));
                nombre_buts.add(buts);
            }

        } catch (JSONException e) {
            Log.d("JoueursJson",e.getMessage());
            e.printStackTrace();
        }

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public List<Buts> getNombre_buts() {
        return nombre_buts;
    }

    public void setNombre_buts(List<Buts> nombre_buts) {
        this.nombre_buts = nombre_buts;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

}
