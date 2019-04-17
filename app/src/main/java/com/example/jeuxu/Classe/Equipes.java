package com.example.jeuxu.Classe;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Equipes {
    public String ID;
    public String nomEquipe;
    public List<Joueur> joueurList=new ArrayList<>();
    private List <Statistique_rencontre> statistiqueRencontres=new ArrayList<>();

    public String link;
    public String nomManager;
    public String IDcategorie;
   private Joueur joueur;

    public Equipes(JSONObject o) {
        try {
            this.nomEquipe = o.getString("nom_equipe");

            this.ID = o.getString("id_equipe");
            this.link = o.getJSONObject("univerciter").getString("icon");
            this.nomManager =o.getString("nom_manager");
            JSONArray jsonArrayJoueurs = o.getJSONArray("joueurs");
            JSONArray jsonArrayRencontre = o.getJSONArray("statistics");
            for (int i = 0; i < jsonArrayRencontre.length(); i++) {
                Statistique_rencontre statistique_rencontre = new Statistique_rencontre(jsonArrayRencontre.getJSONObject(i));
                statistiqueRencontres.add(statistique_rencontre);
            }
            for (int i = 0; i < jsonArrayJoueurs.length(); i++) {
                 joueur = new Joueur(jsonArrayJoueurs.getJSONObject(i));
                Log.d("EquipeJson",joueur.poste);
                joueurList.add(joueur);
            }
        } catch (JSONException e) {
            Log.d("EquipeJson",e.getMessage());
            e.printStackTrace();
        }
    }
    public List<Statistique_rencontre> getStatistiqueRencontres() {
        return statistiqueRencontres;
    }

    public void setStatistiqueRencontres(List<Statistique_rencontre> statistiqueRencontres) {
        this.statistiqueRencontres = statistiqueRencontres;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public List<Joueur> getJoueurList() {
        return joueurList;
    }

    public void setJoueurList(List<Joueur> joueurList) {
        this.joueurList = joueurList;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNomManager() {
        return nomManager;
    }

    public void setNomManager(String nomManager) {
        this.nomManager = nomManager;
    }

    public String getIDcategorie() {
        return IDcategorie;
    }

    public void setIDcategorie(String IDcategorie) {
        this.IDcategorie = IDcategorie;
    }
}
