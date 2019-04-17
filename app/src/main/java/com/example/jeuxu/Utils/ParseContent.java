package com.example.jeuxu.Utils;

import android.app.Activity;
import android.util.Log;

import com.example.jeuxu.Acceuil.Classe.news;
import com.example.jeuxu.Actualites.Classes.Article_revue;
import com.example.jeuxu.Classe.Client;
import com.example.jeuxu.Classe.Preference;
import com.example.jeuxu.Classe.Rencontre;
import com.example.jeuxu.Classement.Classe.Classem;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.Securiter.Classe.numero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.jeuxu.Utils.AndyConstant.Params.DATE_NAISSANCE;
import static com.example.jeuxu.Utils.AndyConstant.Params.EMAIL;
import static com.example.jeuxu.Utils.AndyConstant.Params.ID_UNIVERCITE;
import static com.example.jeuxu.Utils.AndyConstant.Params.KEY_MSG;
import static com.example.jeuxu.Utils.AndyConstant.Params.KEY_SUCCESS;
import static com.example.jeuxu.Utils.AndyConstant.Params.NAME;
import static com.example.jeuxu.Utils.AndyConstant.Params.NOM_LIEU;
import static com.example.jeuxu.Utils.AndyConstant.Params.PASSWORD;
import static com.example.jeuxu.Utils.AndyConstant.Params.SEXE;
import static com.example.jeuxu.Utils.AndyConstant.Params.SURNAME;
import static com.example.jeuxu.Utils.AndyConstant.Params.TOKEN;

public class ParseContent {
    private Activity activity;

    ArrayList<HashMap<String, String>> arraylist;

    public ParseContent(Activity activity) {
        this.activity = activity;
    }

    public boolean isSuccess (String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString(KEY_SUCCESS).equals("true")) {
                return true;
            } else {

                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getErrorCode(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(KEY_MSG);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    public List<Rencontre>  getInfo(String response){
        Log.d("jsonparse", "parseContent");
        ArrayList<Rencontre> rencontres=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(response);
            if (jsonObject.getString(KEY_SUCCESS).equals("true")){
                arraylist=new ArrayList<>();
                JSONArray dataArray=jsonObject.getJSONArray("msg");
                Log.d("erreurJson","parseContentSuccess");
                for (int i=0;i<dataArray.length();i++){
                    Rencontre rencontre=new Rencontre(dataArray.getJSONObject(i));
                    rencontres.add(rencontre);
                }
            }

        } catch (JSONException e) {
            Log.d("erreurJson",e.getMessage());
            e.printStackTrace();
        }
        return rencontres;
    }
    public Client getInfoClient(String response){
        ArrayList<Client> clients=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(response);
            if (jsonObject.getString(KEY_SUCCESS).equals("true")){
                arraylist=new ArrayList<>();
                JSONArray dataArray=jsonObject.getJSONArray("msg");
                for (int i=0;i<dataArray.length();i++){
                    Client client=new Client();
                    JSONObject dataobj=dataArray.getJSONObject(i);
                    client.setName(dataobj.getString(NAME));
                    client.setSurname(dataobj.getString(SURNAME));
                    client.setSexe(dataobj.getString(SEXE));
                    client.setDate_naissance(dataobj.getString(DATE_NAISSANCE));
                    client.setEmail(dataobj.getString(EMAIL));
                    client.setPassword(dataobj.getString(PASSWORD));
                    client.setNom_lieux(dataobj.getString(NOM_LIEU));
                    client.setID_univercity(dataobj.getString(ID_UNIVERCITE));
                    client.setToken(dataobj.getString(TOKEN));

                    clients.add(client);

                    Common.currentClient=null;
                    Common.currentClient=client;

                }
                return clients.get(0);
            }
        } catch (JSONException e) {
            Log.d("erreurJsonGetInfoClient",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Preference> getInfoPre(String response){
        ArrayList<Preference>preferences=new ArrayList<>();
        try{
            JSONObject jsonObject=new JSONObject(response);
            if (jsonObject.getString(KEY_SUCCESS).equals("true")){
                arraylist=new ArrayList<>();
                JSONArray dataArray=jsonObject.getJSONArray("msg");
                for (int i=0;i<dataArray.length();i++){
                    Preference preference=new Preference(dataArray.getJSONObject(i));
                    preferences.add(preference);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return preferences;
    }

    public List<Article_revue> getInfoArticle(String response){
        ArrayList<Article_revue>article_revues=new ArrayList<>();
        try{
            JSONObject jsonObject=new JSONObject(response);
            if (jsonObject.getString(KEY_SUCCESS).equals("true")){
                arraylist=new ArrayList<>();
                JSONArray dataArray=jsonObject.getJSONArray("msg");
                for (int i=0;i<dataArray.length();i++){
                    Article_revue article_revue=new Article_revue(dataArray.getJSONObject(i));
                    article_revues.add(article_revue);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return article_revues;
    }
    public List<news> getInfoActu(String response){
        ArrayList<news>news=new ArrayList<>();
        try{
            JSONObject jsonObject=new JSONObject(response);
            if (jsonObject.getString(KEY_SUCCESS).equals("true")){
                arraylist=new ArrayList<>();
                JSONArray dataArray=jsonObject.getJSONArray("msg");
                for (int i=0;i<dataArray.length();i++){
                    news news1=new news(dataArray.getJSONObject(i));
                    news.add(news1);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }
    public List<numero> getInfoNumero(String response){
        ArrayList<numero>numeros=new ArrayList<>();
        try{
            JSONObject jsonObject=new JSONObject(response);
            if (jsonObject.getString(KEY_SUCCESS).equals("true")){
                arraylist=new ArrayList<>();
                JSONArray dataArray=jsonObject.getJSONArray("msg");
                for (int i=0;i<dataArray.length();i++){
                    numero numeroo=new numero(dataArray.getJSONObject(i));
                    numeros.add(numeroo);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return numeros;
    }
    public List<Classem>getInfosClass(String response){
        ArrayList<Classem>classems=new ArrayList<>();
        try{
            JSONObject jsonObject=new JSONObject(response);
            if (jsonObject.getString(KEY_SUCCESS).equals("true")){
                arraylist=new ArrayList<>();
                JSONArray dataArray=jsonObject.getJSONArray("msg");
                Log.d("getInfosClass","valide");
                for (int i=0;i<dataArray.length();i++){
                    Classem classem=new Classem(dataArray.getJSONObject(i));
                    classems.add(classem);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return classems;
    }


}
