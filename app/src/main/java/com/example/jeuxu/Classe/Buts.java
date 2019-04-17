package com.example.jeuxu.Classe;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Buts {
    public String date_buts;
    public String unite;

    public Buts(JSONObject jsonObject){
        try {
            this.date_buts = jsonObject.getString("date");
            this.unite = jsonObject.getString("unite");
            Log.d("JoueursJson",this.unite);

        } catch (JSONException e) {
            Log.d("JoueursJson",e.getMessage());
            e.printStackTrace();
        }
    }

    public String getDate_buts() {
        return date_buts;
    }

    public void setDate_buts(String date_buts) {
        this.date_buts = date_buts;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}
