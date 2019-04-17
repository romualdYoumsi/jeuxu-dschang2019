package com.example.jeuxu.Classe;

import android.util.Log;

import com.example.jeuxu.Detail_Sport.Fragment_Detail.Statist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class Rencontre {
    private String ID;
    private Lieu lieu;
    private String date;
    private int commencer;
    private int terminer;
    private List<Equipes> equipesList=new ArrayList<>();
    private Poule poule;
    private String categorie;
    private boolean isFavoris;

    public Rencontre(JSONObject jsonObject) throws JSONException {
        this.ID = jsonObject.getString("id_rencontre");
        this.date=jsonObject.getString("date");
        this.lieu = new Lieu(jsonObject.getJSONObject("lieux"));
        this.commencer = jsonObject.getInt("commencer");
        this.terminer = jsonObject.getInt("terminer");
        //this.isFavoris=jsonObject.getBoolean("favoris");
        JSONArray jsonArrayEquipe = jsonObject.getJSONArray("equipes");

        for (int i = 0; i < jsonArrayEquipe.length(); i++) {
            Equipes equipes = new Equipes(jsonArrayEquipe.getJSONObject(i));
            equipesList.add(equipes);
        }

    }

    public boolean isFavoris() {
        return isFavoris;
    }

    public void setFavoris(boolean favoris) {
        isFavoris = favoris;
    }

    public int getCommencer() {
        return commencer;
    }

    public void setCommencer(int commencer) {
        this.commencer = commencer;
    }

    public int getTerminer() {
        return terminer;
    }

    public void setTerminer(int terminer) {
        this.terminer = terminer;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Equipes> getEquipesList() {
        return equipesList;
    }

    public void setEquipesList(List<Equipes> equipesList) {
        this.equipesList = equipesList;
    }

    public Poule getPoule() {
        return poule;
    }

    public void setPoule(Poule poule) {
        this.poule = poule;

    }



    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
