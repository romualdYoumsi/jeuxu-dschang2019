package com.example.jeuxu.Securiter.Classe;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class numero {

    private String ID;
    List<dataSecuriter> securiters = new ArrayList<>();

    public numero(JSONObject jsonObject) {
        try {
            JSONArray jsonArra = jsonObject.getJSONArray("data");

           for (int i=0;i<securiters.size();i++){
               this.ID=jsonArra.getJSONObject(i).getString("titre");

           }

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

    public List<dataSecuriter> getSecuriters() {
        return securiters;
    }

    public void setSecuriters(List<dataSecuriter> securiters) {
        this.securiters = securiters;
    }
}
