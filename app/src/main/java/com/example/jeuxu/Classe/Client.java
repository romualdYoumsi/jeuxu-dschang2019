package com.example.jeuxu.Classe;

public class Client {
    private String ID;
    private String name;
    private String surname;
    private String password;
    private String lat;
    private String lng;
    private String nom_lieux;
    private String ID_univercity;
    private String email;
    private String date_naissance;
    private String localite;
    private String sexe;
    private String token;

    public Client() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getNom_lieux() {
        return nom_lieux;
    }

    public void setNom_lieux(String nom_lieux) {
        this.nom_lieux = nom_lieux;
    }

    public String getID_univercity() {
        return ID_univercity;
    }

    public void setID_univercity(String ID_univercity) {
        this.ID_univercity = ID_univercity;
    }

    public String getLocalite() {
        return localite;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
