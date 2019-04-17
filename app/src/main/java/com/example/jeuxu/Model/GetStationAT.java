package com.example.jeuxu.Model;

import android.os.AsyncTask;

import com.example.jeuxu.Classe.Station;

import java.util.ArrayList;

public class GetStationAT extends AsyncTask {

    private ArrayList<Station> stations;
    private  Exception exception;

    public GetStationAT(GetStationATResult getStationATResult) {
        this.getStationATResult = getStationATResult;
    }

    private GetStationATResult getStationATResult;



    @Override
    protected Object doInBackground(Object[] objects) {
        try {
           stations= WSUtils.getStations();
        } catch (Exception e) {
            exception=e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (exception!=null){
            getStationATResult.getStationATResultErreur(exception);
        }
        else {
            getStationATResult.stationCharger(stations);
        }

    }
    public interface GetStationATResult{
        void stationCharger(ArrayList<Station>stations);
        void getStationATResultErreur(Exception exception);
    }
}
