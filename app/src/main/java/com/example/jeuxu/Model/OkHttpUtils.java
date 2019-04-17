package com.example.jeuxu.Model;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {

    public static Response sendGetOkHttpRequest(String url) throws Exception {
        Log.v("TAG_URL", url);
        OkHttpClient client = new OkHttpClient();
        //Creation de la requete
        Request request = new Request.Builder().url(url).build();
        //Execution de la requete
        return client.newCall(request).execute();
    }

}
