package com.example.jeuxu.Actualites.Classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Article_revue {
    private String ID;
    private String titre;
    private String description;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Article_revue(JSONObject jsonObject) {
        try {
            this.ID=jsonObject.getString("id_revue");
            this.description=jsonObject.getString("description");
            this.link=jsonObject.getString("link");
            this.titre=jsonObject.getString("titre");
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
