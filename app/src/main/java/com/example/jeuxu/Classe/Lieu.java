package com.example.jeuxu.Classe;

import org.json.JSONException;
import org.json.JSONObject;

public class Lieu {
    private String nom_lieu;
    private String lng;
    private String lat;

    public Lieu(JSONObject lieu) {
        try {
            this.nom_lieu = lieu.getString("nom_lieux");
            this.lat = lieu.getString("lat");
            this.lng = lieu.getString("lng");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getNom_lieu() {
        return nom_lieu;
    }

    public void setNom_lieu(String nom_lieu) {
        this.nom_lieu = nom_lieu;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
