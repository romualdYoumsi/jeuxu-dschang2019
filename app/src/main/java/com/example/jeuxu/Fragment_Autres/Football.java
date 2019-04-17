package com.example.jeuxu.Fragment_Autres;


import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jeuxu.AdapterClass.RencontreAdapter;
import com.example.jeuxu.Classe.Rencontre;
import com.example.jeuxu.R;
import com.example.jeuxu.Utils.AndyConstant;
import com.example.jeuxu.Utils.AndyUtils;
import com.example.jeuxu.Utils.HttpRequest;
import com.example.jeuxu.Utils.ParseContent;

import java.util.List;
import java.util.Locale;


public class Football extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    RecyclerView lstdata;
    private SwipeRefreshLayout refresh;
    private ParseContent parseContent;
    private final int jsoncode = 1;
    private List<Rencontre> rencontreList;
    private PendingIntent pendingIntent;
    private  Exception exception;


    public Football() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_football, container, false);
        lstdata = (RecyclerView) view.findViewById(R.id.lst_menu);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.recharger);
        refresh.setOnRefreshListener(this);
        parseContent = new ParseContent(getActivity());
        setRetainInstance(true);
        parseJson();


        return view;

    }


    public void display(List<Rencontre> rencontreList) {
        RencontreAdapter rencontreAdapter = new RencontreAdapter(getActivity().getApplicationContext(), rencontreList);
        lstdata.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));
        lstdata.setAdapter(rencontreAdapter);

    }

    @SuppressLint("StaticFieldLeak")
    private void parseJson() {

        if (!AndyUtils.isNetworkAvailable(getActivity().getApplicationContext())) {
            Toast.makeText(getActivity().getApplicationContext(), "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }

        AndyUtils.showSimpleProgressDialog(getActivity().getApplicationContext());

        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                exception=null;
            }

            protected String doInBackground(Void[] params) {
                String response = "";

                try {
                    HttpRequest req = new HttpRequest(AndyConstant.ServiceType.FOOT_TAG);
                    response = req.prepare(HttpRequest.Method.GET).sendAndReadString();

                } catch (Exception e) {
                    exception = e;
                }
                return response;
            }


            protected void onPostExecute(String result) {
                //do something with response
                if(exception==null){
                    onTaskCompleted(result, jsoncode);
                }else{
                    Log.d("TAGONPOST",exception.getMessage());
                }

            }
        }.execute();
    }

    public void onTaskCompleted(String response, int serviceCode) {

        switch (serviceCode) {
            case jsoncode:
                AndyUtils.removeSimpleProgressDialog();
                if (parseContent.isSuccess(response)) {
                    rencontreList = parseContent.getInfo(response);
                    display(rencontreList);
                    Log.d("responsejson", response);

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }


    @Override
    public void onResume() {

        super.onResume();

    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                parseJson();
                refresh.setRefreshing(false);

            }
        }, 2000);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }


}
