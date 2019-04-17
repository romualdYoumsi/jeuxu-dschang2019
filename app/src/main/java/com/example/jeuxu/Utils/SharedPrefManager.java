package com.example.jeuxu.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME="mysharednjiketude";
    private static final String KEY_USER_ID="id";
    private static final String KEY_USER_NAME="name";
    private static final String KEY_USER_EMAIL="email";
    private static final String KEY_USER_SURNAME="surname";
    private static final String KEY_USER_DATE_N="date_naissance";

    private static final String KEY_USER_LIEU="nom_lieu";
    private static final String KEY_USER_UNIVER="id_univercity";

    private SharedPrefManager(Context context){
        mCtx=context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance==null){
            mInstance= new SharedPrefManager(context);
        }
        return mInstance;
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE );
        if (sharedPreferences.getString(KEY_USER_EMAIL,null)!=null){
            return true;
        }
        return false;
    }
    public boolean userlogin(String name, String email,String date_naissance,String nom_lieu,String surname,String id_univercity ){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();


        editor.putString(KEY_USER_NAME,name);
        editor.putString(KEY_USER_EMAIL,email);
        editor.putString(KEY_USER_DATE_N,date_naissance);
        editor.putString(KEY_USER_SURNAME,surname);
        editor.putString(KEY_USER_LIEU,nom_lieu);
        editor.putString(KEY_USER_UNIVER,id_univercity);

        editor.apply();

        return true;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(KEY_USER_EMAIL,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.clear();
        editor.apply();
        return true;
    }
    public String getUserName(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_NAME,null);
    }
    public String getKeyUserSurname(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_SURNAME,null);
    }
    public String getKeyUserId(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_ID,null);
    }
    public String getUserEmail(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL,null);
    }
    public String getKeyUserUniver(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_UNIVER,null);
    }
    public String getKeyUserLieu(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_LIEU,null);
    }

    public String getUserDateN(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_DATE_N,null);
    }
}
