package com.example.jeuxu.Classement.Classe;

import org.json.JSONException;
import org.json.JSONObject;

public class Classem  {

    private String ID;
    private String name_uni;
    private String bronze;
    private String argent;
    private String or;
    private String link;

    public Classem(JSONObject jsonObject) {
        try {
            this.name_uni=jsonObject.getString("nom");
            this.link=jsonObject.getString("icon");
            jsonObject=jsonObject.getJSONObject("medailles");
            this.argent=jsonObject.getString("ARGENT");
            this.or=jsonObject.getString("OR");
            this.bronze=jsonObject.getString("BRONZE");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName_uni() {
        return name_uni;
    }

    public void setName_uni(String name_uni) {
        this.name_uni = name_uni;
    }

    public String getBronze() {
        return bronze;
    }

    public void setBronze(String bronze) {
        this.bronze = bronze;
    }

    public String getArgent() {
        return argent;
    }

    public void setArgent(String argent) {
        this.argent = argent;
    }

    public String getOr() {
        return or;
    }

    public void setOr(String or) {
        this.or = or;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
