package com.example.jeuxu.Inscription.Fragment_inscription;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.jeuxu.AdapterClass.PreferenceAdapter;
import com.example.jeuxu.Choix_Connexion;
import com.example.jeuxu.Classe.Client;
import com.example.jeuxu.Classe.Preference;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.MainActivity;
import com.example.jeuxu.R;
import com.example.jeuxu.Utils.AndyConstant;
import com.example.jeuxu.Utils.AndyUtils;
import com.example.jeuxu.Utils.DatabaseHelper;
import com.example.jeuxu.Utils.HttpRequest;
import com.example.jeuxu.Utils.ParseContent;
import com.example.jeuxu.Utils.SharedPrefManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inscription_three extends Fragment {
    DatabaseHelper databaseHelper;
    RecyclerView lstdata;
    private ParseContent parseContent;
    private final int jsoncode = 1;
    private List<Preference> preferences;
    Button btnTerminer;
    private Client client;


    public Inscription_three() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_inscription_three, container, false);
        btnTerminer = (Button) view.findViewById(R.id.btnTerminer);
        lstdata = (RecyclerView) view.findViewById(R.id.lst_discipline);
        databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());
        super.onCreate(savedInstanceState);
        parseContent = new ParseContent(getActivity());
        parseJsonPref();

        btnTerminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsejson();
            }
        });

        return view;

    }

    public void display(List<Preference> preferences) {
        PreferenceAdapter preferenceAdapter = new PreferenceAdapter(getActivity().getApplicationContext(), preferences);
        lstdata.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));
        lstdata.setAdapter(preferenceAdapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void parseJsonPref() {

        if (!AndyUtils.isNetworkAvailable(getActivity().getApplicationContext())) {
            Toast.makeText(getActivity().getApplicationContext(), "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }
        AndyUtils.showSimpleProgressDialog(getActivity().getApplicationContext());


        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                String response = "";
                try {
                    HttpRequest request = new HttpRequest(AndyConstant.ServiceType.REFERENCE);
                    response = request.prepare(HttpRequest.Method.GET).sendAndReadString();


                } catch (IOException e) {
                    response = e.getMessage();
                }
                return response;
            }

            @Override
            protected void onPostExecute(String s) {
                onTaskCompletedPref(s, jsoncode);
            }
        }.execute();
    }

    private void onTaskCompletedPref(String response, int serviceCode) {
        switch (serviceCode) {
            case jsoncode:
                AndyUtils.removeSimpleProgressDialog();
                if (parseContent.isSuccess(response)) {
                    preferences = parseContent.getInfoPre(response);
                    display(preferences);
                    Log.d("responsejson", response);


                } else {
                    Toast.makeText(getActivity().getApplicationContext(), parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void onPostExecute(Intent intent) {
        Toast.makeText(getContext(), "Bienvenue " + Common.currentClient.getName(), Toast.LENGTH_LONG).show();
        SharedPrefManager.getInstance(getContext()).userlogin(Common.currentClient.getName(), Common.currentClient.getEmail(), Common.currentClient.getDate_naissance()
                , Common.currentClient.getNom_lieux(), Common.currentClient.getSurname(), Common.currentClient.getID_univercity());
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        parseJsonPref();
    }

    private void parsejson() {
        if (!AndyUtils.isNetworkAvailable(getContext())) {
            Toast.makeText(getContext(), "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }
        AndyUtils.showSimpleProgressDialog(getContext());


        new AsyncTask<Void, Void, String>() {


            @Override
            protected String doInBackground(Void... voids) {
                String response = "";
                HashMap<String, String> map = new HashMap<>();
                map.put("nom", Common.currentClient.getName());
                map.put("prenom", Common.currentClient.getSurname());
                map.put("sexe", Common.currentClient.getSexe());
                map.put("telephone", Common.currentClient.getDate_naissance());
                map.put("email", Common.currentClient.getEmail());
                map.put("password", Common.currentClient.getPassword());
                map.put("ville", Common.currentClient.getNom_lieux());
                map.put("univercite", "null");

                try {
                    HttpRequest request = new HttpRequest(AndyConstant.ServiceType.REGISTER);
                    response = request.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();


                } catch (IOException e) {
                    response = e.getMessage();
                }
                return response;
            }

            @Override
            protected void onPostExecute(String s) {
                Log.d("REGISTER_TWO", s);
                onTaskCompleted(s, jsoncode);

            }
        }.execute();
    }

    private void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response.toString());
        switch (serviceCode) {
            case jsoncode:
                AndyUtils.removeSimpleProgressDialog();
                if (parseContent.isSuccess(response)) {
                    client = parseContent.getInfoClient(response);
                    final Intent intent = new Intent(getContext(), MainActivity.class);

                    Gson gson = new Gson();
                    String myJson = gson.toJson(client);

                    intent.putExtra("user", myJson);
                    AddData(Common.currentClient.getName(),Common.currentClient.getSurname(),Common.currentClient.getSexe(),Common.currentClient.getID_univercity(),Common.currentClient.getNom_lieux()
                    ,Common.currentClient.getDate_naissance(),Common.currentClient.getEmail(),Common.currentClient.getToken());
                    onPostExecute(intent);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();


                }

        }
    }

    public void AddData(String NOM,String PRENOM,String SEXE,String UNIVERSITE,String LOCALISE,String NUMERO,String EMAIL,String Token){
        boolean insertData=databaseHelper.addData(NOM,PRENOM,SEXE,UNIVERSITE,LOCALISE,NUMERO,EMAIL,Token);

        if (insertData){
        }
        else {
            toastMessage("Something went wrong");
        }

    }
    public void toastMessage(String message){
        Toast.makeText(getActivity().getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

}
