package com.example.jeuxu.Classe;

public class Poule {
    private String ID;
    private String nom;

    public Poule(String ID, String nom) {
        this.ID = ID;
        this.nom = nom;
    }

    public Poule() {
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
}
