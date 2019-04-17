package com.example.jeuxu.Fragment_Autres;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Basket extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView lstdata;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout refresh;
    private ParseContent parseContent;
    private final int jsoncode = 1;
    private List<Rencontre> rencontreList;



    public Basket() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        lstdata = (RecyclerView) view.findViewById(R.id.lst_menu);
        refresh=(SwipeRefreshLayout)view.findViewById(R.id.recharger);
        refresh.setOnRefreshListener(this);

        super.onCreate(savedInstanceState);


        parseContent = new ParseContent(getActivity());


        progressDialog = new ProgressDialog(container.getContext());
        progressDialog.setMessage("Chargement ...");
        parseJson();



        return view;

    }



    public void display(List<Rencontre> rencontreList) {
        RencontreAdapter rencontreAdapter = new RencontreAdapter(getActivity().getApplicationContext(), rencontreList);
        lstdata.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),1));
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

            protected String doInBackground(Void[] params) {
                String response = "";

                try {
                    HttpRequest req = new HttpRequest(AndyConstant.ServiceType.BASKETBALL_TAG);
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
                    rencontreList = parseContent.getInfo(response);
                    display(rencontreList);
                    Log.d("responsejson", response);

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        parseJson();
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
