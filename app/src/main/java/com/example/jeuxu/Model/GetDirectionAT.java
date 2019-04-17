package com.example.jeuxu.Model;

import android.os.AsyncTask;

import com.example.jeuxu.Classe.Station;

import java.util.ArrayList;

public class GetDirectionAT extends AsyncTask {

    private  Exception exception;


    private GetDirectionAT.GetDirectionATResult getDirectionATResult;

    public GetDirectionAT(GetDirectionATResult getDirectionATResult) {
        this.getDirectionATResult = getDirectionATResult;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
           WSUtils.getDirectiontion();
        } catch (Exception e) {
            exception=e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (getDirectionATResult!=null){
            if (exception!=null){
                getDirectionATResult.getdirectionATResultErreur(exception);
            }
            else {
                getDirectionATResult.directionCharger();
            }
        }



    }
    public interface GetDirectionATResult{
        void directionCharger();
        void getdirectionATResultErreur(Exception exception);
    }
}
