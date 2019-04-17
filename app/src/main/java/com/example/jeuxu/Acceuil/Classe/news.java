package com.example.jeuxu.Acceuil.Classe;

import org.json.JSONException;
import org.json.JSONObject;

public class news {
    private String ID;
    private String titre;
    private String description;
    private String link;
    private String flux;

    public news(JSONObject jsonObject) {
        try {
           // jsonObject=jsonObject.getJSONObject("msg");
            this.link=jsonObject.getString("icon");
            this.titre=jsonObject.getString("titre");
            this.description=jsonObject.getString("description");
            this.ID=jsonObject.getString("id_actualite");
            this.flux=jsonObject.getString("flux");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getFlux() {
        return flux;
    }

    public void setFlux(String flux) {
        this.flux = flux;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
