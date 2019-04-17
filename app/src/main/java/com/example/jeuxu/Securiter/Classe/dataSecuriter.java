package com.example.jeuxu.Securiter.Classe;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class dataSecuriter {

    private String titre;
    private String numero;


    public dataSecuriter(JSONObject o) {
        try {
            this.titre=o.getString("name");
            this.numero=o.getString("numero");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
