package com.example.jeuxu.Classe;

import org.json.JSONException;
import org.json.JSONObject;

public class Preference {
    private String ID;
    private String name;
    private String link;


    public Preference(JSONObject jsonObject)throws JSONException {

        this.ID=jsonObject.getString("id_discipline");
        this.link=jsonObject.getString("icon");
        this.name=jsonObject.getString("nom");


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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
