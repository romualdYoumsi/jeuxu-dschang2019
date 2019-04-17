package com.example.jeuxu.Acceuil.Fragment_Acceuil;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jeuxu.Acceuil.Adapter.ActuAdapter;
import com.example.jeuxu.Acceuil.Classe.news;
import com.example.jeuxu.R;
import com.example.jeuxu.Utils.AndyConstant;
import com.example.jeuxu.Utils.AndyUtils;
import com.example.jeuxu.Utils.HttpRequest;
import com.example.jeuxu.Utils.ParseContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Athletisme_acc extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView lstdata;
    private SwipeRefreshLayout refresh;
    private ParseContent parseContent;
    private final int jsoncode = 1;
    List<news> newsList=new ArrayList<>();
    public Athletisme_acc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_athletisme_acc, container, false);
        lstdata = (RecyclerView) view.findViewById(R.id.lst_foot);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.recharger);
        refresh.setOnRefreshListener(this);
        parseContent = new ParseContent(getActivity());
        parseJson();
        return view;
    }
    public void parseJson(){
        if (!AndyUtils.isNetworkAvailable(getActivity().getApplicationContext())) {
            Toast.makeText(getActivity().getApplicationContext(), "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }

        AndyUtils.showSimpleProgressDialog(getActivity().getApplicationContext());

        new AsyncTask<Void, Void, String>() {

            protected String doInBackground(Void[] params) {
                String response = "";

                try {
                    HttpRequest req = new HttpRequest(AndyConstant.ServiceType.ATHLETISME_ACC);
                    response = req.prepare(HttpRequest.Method.GET).sendAndReadString();

                } catch (Exception e) {
                    response = e.getMessage();
                }
                return response;
            }


            protected void onPostExecute(String result) {
                //do something with response

                onTaskCompleted(result, jsoncode);
            }
        }.execute();
    }
    public void onTaskCompleted(String response, int serviceCode) {

        switch (serviceCode) {
            case jsoncode:
                AndyUtils.removeSimpleProgressDialog();
                if (parseContent.isSuccess(response)) {
                    newsList = parseContent.getInfoActu(response);
                    display(newsList);
                    Log.d("responsejson", response);

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }
    public void display(List<news> news){
        ActuAdapter actuAdapter=new ActuAdapter(getActivity().getApplicationContext(),news);
        lstdata.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),1));
        lstdata.setAdapter(actuAdapter);
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

}
