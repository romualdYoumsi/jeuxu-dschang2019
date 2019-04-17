package com.example.jeuxu.Model;

import android.util.Log;

import com.example.jeuxu.Classe.Station;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import okhttp3.Response;

import static com.example.jeuxu.Utils.AndyConstant.ServiceType.BASEURL;

public class WSUtils {

    private static final String URL = BASEURL+"/ju/location.json";

    private static final Gson gson = new Gson();

    public static ArrayList<Station> getStations() throws Exception {
        Response response = OkHttpUtils.sendGetOkHttpRequest(URL);

        if (response.code() != HttpURLConnection.HTTP_OK) {
            throw new Exception("Reponse du serveur incorrecte : " + response.code());
        } else {
            InputStreamReader inputStreamReader=new InputStreamReader(response.body().byteStream());
            ArrayList<Station> stations = gson.fromJson(inputStreamReader, new TypeToken<ArrayList<Station>>() {

            }.getType());
            inputStreamReader.close();
            return stations;
        }

    }

    private final static String URL_WS_GOOGLE_JSON="https://maps.googleapis.com/maps/api/directions/json?mode=walking";
    public static  void getDirectiontion() throws Exception{
        String url=URL_WS_GOOGLE_JSON+"&origin="+"43.623673,1.389937"+"&destination="+"43.630397,1.407493";
        Response response=OkHttpUtils.sendGetOkHttpRequest(url);

        Log.v("TAGURL",response.body().string());
    }
}
